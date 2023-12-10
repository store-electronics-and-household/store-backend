package ru.acceleration.store.service.attributes;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.acceleration.store.dto.attribute.ModelAttributeDtoResponse;
import ru.acceleration.store.mapper.ModelAttributeMapper;
import ru.acceleration.store.model.ModelAttribute;
import ru.acceleration.store.repository.AttributeRepository;
import ru.acceleration.store.repository.ModelAttributeRepository;
import ru.acceleration.store.repository.ModelRepository;
import ru.acceleration.store.service.attribute.ModelAttributeServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModelAttributesServiceTests {

    @InjectMocks
    ModelAttributeServiceImpl modelAttributeService;

    @Mock
    ModelAttributeRepository modelAttributeRepository;

    @Mock
    AttributeRepository attributeRepository;

    @Mock
    ModelRepository modelRepository;

    @Captor
    ArgumentCaptor<ModelAttribute> captor;

    @Spy
    ModelAttributeMapper modelAttributeMapper = Mappers.getMapper(ModelAttributeMapper.class);

//    @Test
//    public void getModelAttributeById_givenValidId_expectSuccess() {
//        ModelAttribute modelAttribute = ModelAttribute.builder().id(17L).value("Value").build();
//        when(modelAttributeRepository.findById(17L)).thenReturn(Optional.of(modelAttribute));
//
//        ModelAttributeDtoResponse modelAttributeDtoResponse = modelAttributeService
//                .getModelAttributesById(17L);
//
//        assertNotNull(modelAttributeDtoResponse);
//        assertEquals(17L, modelAttributeDtoResponse.getId());
//        assertEquals("Value", modelAttributeDtoResponse.getValue());
//        verify(modelAttributeRepository, atLeast(1)).findById(17L);
//    }
}
