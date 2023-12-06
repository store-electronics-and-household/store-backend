package ru.acceleration.store.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import ru.acceleration.store.dto.attribute.AttributeDtoRequest;
import ru.acceleration.store.dto.attribute.AttributeDtoResponse;
import ru.acceleration.store.exceptions.BadRequestException;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.AttributeMapper;
import ru.acceleration.store.model.Attribute;
import ru.acceleration.store.repository.AttributeRepository;
import ru.acceleration.store.service.attribute.AttributeServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttributesServiceTests {

    @InjectMocks
    AttributeServiceImpl attributeServiceImpl;

    @Mock
    AttributeRepository attributeRepository;

    @Spy
    @InjectMocks
    AttributeMapper attributeMapper = Mappers.getMapper(AttributeMapper.class);

    @Test
    public void getAttributeById_givenValidId() {
        Attribute attribute = Attribute.builder().id(17L).name("AttributeName").build();
        when(attributeRepository.findById(17L)).thenReturn(Optional.of(attribute));

        AttributeDtoResponse attributeDtoResponse = attributeServiceImpl.getAttributeById(17L);

        assertNotNull(attributeDtoResponse);
        assertEquals(17L, attributeDtoResponse.getId());
        assertEquals("AttributeName", attributeDtoResponse.getName());
        verify(attributeRepository, atLeast(1)).findById(17L);
    }

    @Test
    public void getAttributeById_givenInvalidId() {
        when(attributeRepository.findById(17L)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, ()-> attributeServiceImpl.getAttributeById(17L));
        verify(attributeRepository, atLeast(1)).findById(17L);
    }

    @Test
    public void createAttribute_givenValidData() {
        Attribute attribute = Attribute.builder().id(1L).name("AttributeName").build();
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder().name("AttributeName").build();
        when(attributeRepository.save(any(Attribute.class))).thenReturn(attribute);

        AttributeDtoResponse attributeDtoResponse = attributeServiceImpl.createAttribute(attributeDtoRequest);

        assertNotNull(attributeDtoResponse);
        assertEquals(1L, attributeDtoResponse.getId());
        assertEquals("AttributeName", attributeDtoResponse.getName());
        verify(attributeRepository, atLeast(1)).save(any(Attribute.class));
    }

    @Test
    public void createAttribute_givenAttributeNameNull() {
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder().name(null).build();

        assertThrows(BadRequestException.class, () -> attributeServiceImpl.createAttribute(attributeDtoRequest));
    }

    @Test
    public void createAttribute_givenAttributeNameEmpty() {
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder().name("").build();

        assertThrows(BadRequestException.class, () -> attributeServiceImpl.createAttribute(attributeDtoRequest));
    }

    @Test
    public void createAttribute_givenAttributeNameOver100Chars() {
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder()
                .name(String.format("%101c", '1')).build();

        assertThrows(BadRequestException.class, () -> attributeServiceImpl.createAttribute(attributeDtoRequest));
    }

    @Test
    public void pathAttribute_givenValidData() {
        Attribute attribute = Attribute.builder().id(17L).name("NewAttributeName").build();
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder().name("NewAttributeName").build();
        when(attributeRepository.save(any(Attribute.class))).thenReturn(attribute);
        when(attributeRepository.findById(17L)).thenReturn(Optional.of(attribute));

        AttributeDtoResponse attributeDtoResponse = attributeServiceImpl.patchAttribute(attributeDtoRequest, 17L);

        assertNotNull(attributeDtoResponse);
        assertEquals(17L, attributeDtoResponse.getId());
        assertEquals("NewAttributeName", attributeDtoResponse.getName());
        verify(attributeRepository, atLeast(1)).save(any(Attribute.class));
        verify(attributeRepository, atLeast(1)).findById(17L);
    }

    @Test
    public void pathAttribute_givenInvalidId() {
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder().name("NewAttributeName").build();
        when(attributeRepository.findById(17L)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> attributeServiceImpl.patchAttribute(attributeDtoRequest, 17L));
        verify(attributeRepository, atLeast(1)).findById(17L);
    }

    @Test
    public void patchAttribute_givenAttributeNameNull() {
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder().name(null).build();

        assertThrows(BadRequestException.class, () -> attributeServiceImpl.patchAttribute(attributeDtoRequest, 17L));
    }

    @Test
    public void patchAttribute_givenAttributeNameEmpty() {
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder().name("").build();

        assertThrows(BadRequestException.class, () -> attributeServiceImpl.patchAttribute(attributeDtoRequest, 17L));
    }

    @Test
    public void patchAttribute_givenAttributeNameOver100Chars() {
        AttributeDtoRequest attributeDtoRequest = AttributeDtoRequest.builder()
                .name(String.format("%101c", '1')).build();

        assertThrows(BadRequestException.class, () -> attributeServiceImpl.patchAttribute(attributeDtoRequest, 17L));
    }

    @Test
    public void deleteAttribute_givenValidData() {
        Attribute attribute = Attribute.builder().id(17L).name("NewAttributeName").build();
        when(attributeRepository.findById(17L)).thenReturn(Optional.of(attribute));
        doNothing().when(attributeRepository).delete(any(Attribute.class));

        attributeServiceImpl.deleteAttribute(17L);

        verify(attributeRepository, atLeast(1)).findById(17L);
        verify(attributeRepository, atLeast(1)).delete(any(Attribute.class));
    }

    @Test
    public void findAttributes_givenValidData() {
        List<Attribute> attributesList =
                List.of(Attribute.builder().id(17L).name("name").build());
        when(attributeRepository.findByNameContainingIgnoreCase("na", PageRequest.of(0,10)))
                .thenReturn(attributesList);

        List<AttributeDtoResponse> attributeDtoResponseList = attributeServiceImpl.findAttributes("na", 0, 10);

        assertNotNull(attributeDtoResponseList);
        assertEquals(1, attributeDtoResponseList.size());
        assertEquals(17L, attributeDtoResponseList.get(0).getId());
        assertEquals("name", attributeDtoResponseList.get(0).getName());
        verify(attributeRepository, atLeast(1))
                .findByNameContainingIgnoreCase("na", PageRequest.of(0, 10));
    }

    @Test
    public void findAttributes_givenInvalidFromParam() {
        assertThrows(BadRequestException.class, () -> attributeServiceImpl.findAttributes("na", -1, 10));
    }

    @Test
    public void findAttributes_givenInvalidSizeParam() {
        assertThrows(BadRequestException.class, () -> attributeServiceImpl.findAttributes("na", 0, 0));
    }
}
