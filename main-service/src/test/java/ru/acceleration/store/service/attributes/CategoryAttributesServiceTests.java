package ru.acceleration.store.service.attributes;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoRequest;
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoResponse;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.CategoryAttributeMapper;
import ru.acceleration.store.model.Attribute;
import ru.acceleration.store.model.Category;
import ru.acceleration.store.model.CategoryAttribute;
import ru.acceleration.store.model.enums.AttributeType;
import ru.acceleration.store.repository.AttributeRepository;
import ru.acceleration.store.repository.CategoryAttributeRepository;
import ru.acceleration.store.repository.CategoryRepository;
import ru.acceleration.store.service.attribute.CategoryAttributesServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryAttributesServiceTests {

    @InjectMocks
    CategoryAttributesServiceImpl categoryAttributesService;

    @Mock
    CategoryAttributeRepository categoryAttributeRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    AttributeRepository attributeRepository;

    @Captor
    ArgumentCaptor<CategoryAttribute> captor;

    @Spy
    CategoryAttributeMapper CategoryAttributemapper = Mappers.getMapper(CategoryAttributeMapper.class);

    @Test
    public void getCategoryAttributeById_givenValidId_thenExpectSuccess() {
        CategoryAttribute categoryAttribute = CategoryAttribute.builder()
                .id(17L)
                .priority(99L)
                .attributeType(AttributeType.THIRD_TYPE)
                .build();
        when(categoryAttributeRepository.findById(17L)).thenReturn(Optional.of(categoryAttribute));

        CategoryAttributeDtoResponse categoryAttributeDtoResponse =
                categoryAttributesService.getCategoryAttributeById(17L);

        assertNotNull(categoryAttributeDtoResponse);
        assertEquals(17L, categoryAttributeDtoResponse.getId());
        assertEquals(99L, categoryAttributeDtoResponse.getPriority());
        assertEquals(AttributeType.THIRD_TYPE, categoryAttributeDtoResponse.getAttributeType());
        verify(categoryAttributeRepository, atLeast(1)).findById(17L);
    }

    @Test
    public void getCategoryAttributeById_givenInvalidId() {
        when(categoryAttributeRepository.findById(17L)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class,
                () -> categoryAttributesService.getCategoryAttributeById(17L));
    }

    @Test
    public void getCategoryAttributeByCategoryId_givenValidId_thenExpectSuccess() {
        Category category = Category.builder().id(19L).name("name").build();
        CategoryAttribute categoryAttribute = CategoryAttribute.builder()
                .id(17L)
                .priority(99L)
                .attributeType(AttributeType.THIRD_TYPE)
                .category(category)
                .build();
        when(categoryRepository.findById(19L)).thenReturn(Optional.of(category));
        when(categoryAttributeRepository.findAllCategoryAttributeByCategoryIdOrderByPriorityDesc(19L))
                .thenReturn(List.of(categoryAttribute));

        List<CategoryAttributeDtoResponse> categoryAttributeDtoResponseList =
                categoryAttributesService.getCategoryAttributesByCategoryId(19L);

        assertNotNull(categoryAttributeDtoResponseList);
        assertEquals(1, categoryAttributeDtoResponseList.size());
        assertEquals(17L, categoryAttributeDtoResponseList.get(0).getId());
        assertEquals(99L, categoryAttributeDtoResponseList.get(0).getPriority());
        assertEquals(AttributeType.THIRD_TYPE, categoryAttributeDtoResponseList.get(0).getAttributeType());
        verify(categoryAttributeRepository, atLeast(1))
                .findAllCategoryAttributeByCategoryIdOrderByPriorityDesc(19L);
        verify(categoryRepository, atLeast(1)).findById(19L);
    }

    @Test
    public void getCategoryAttributeByCategoryId_givenInvalidId_thenExpectNotFound() {
        when(categoryRepository.findById(19L)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class,
                () -> categoryAttributesService.getCategoryAttributesByCategoryId(19L));
    }

    @Test
    public void createCategoryAttribute_givenValidId_thenSuccess() {
        Category category = Category.builder().id(19L).name("CategoryName").build();
        Attribute attribute = Attribute.builder().id(27L).name("attributeName").build();
        CategoryAttributeDtoRequest categoryAttributeDtoRequest = CategoryAttributeDtoRequest.builder()
                .attributeType(AttributeType.FOURTH_TYPE).priority(77L).build();

        when(categoryRepository.findById(19L)).thenReturn(Optional.of(category));
        when(attributeRepository.findById(27L)).thenReturn(Optional.of(attribute));


        categoryAttributesService.createCategoryAttributes(19L, 27L, categoryAttributeDtoRequest);

        verify(categoryAttributeRepository).save(captor.capture());
        CategoryAttribute categoryAttributeSaved = captor.getValue();

        assertNotNull(categoryAttributeSaved);
        assertEquals(77L, categoryAttributeSaved.getPriority());
        assertEquals(AttributeType.FOURTH_TYPE, categoryAttributeSaved.getAttributeType());
        assertEquals(category.getId(), categoryAttributeSaved.getCategory().getId());
        assertEquals(category.getName(), categoryAttributeSaved.getCategory().getName());
        assertEquals(attribute.getId(), categoryAttributeSaved.getAttribute().getId());
        assertEquals(attribute.getName(), categoryAttributeSaved.getAttribute().getName());

        verify(categoryAttributeRepository, atLeast(1)).save(any(CategoryAttribute.class));
        verify(categoryRepository, atLeast(1)).findById(19L);
        verify(attributeRepository, atLeast(1)).findById(27L);
    }

    @Test
    public void createCategoryAttribute_givenInvalidCategoryId_expectNotFound() {
        CategoryAttributeDtoRequest categoryAttributeDtoRequest = CategoryAttributeDtoRequest.builder()
                .attributeType(AttributeType.FOURTH_TYPE).priority(77L).build();

        when(categoryRepository.findById(19L)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> categoryAttributesService
                .createCategoryAttributes(19L, 27L, categoryAttributeDtoRequest));
    }

    @Test
    public void createCategoryAttribute_givenValidAttributeId_expectNotFound() {
        Category category = Category.builder().id(19L).name("CategoryName").build();
        CategoryAttributeDtoRequest categoryAttributeDtoRequest = CategoryAttributeDtoRequest.builder()
                .attributeType(AttributeType.FOURTH_TYPE).priority(77L).build();

        when(categoryRepository.findById(19L)).thenReturn(Optional.of(category));
        when(attributeRepository.findById(27L)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> categoryAttributesService
                .createCategoryAttributes(19L, 27L, categoryAttributeDtoRequest));
    }

    @Test
    public void updateCategoryAttribute_givenValidId_thenSuccess() {
        Category category = Category.builder().id(19L).name("CategoryName").build();
        Attribute attribute = Attribute.builder().id(27L).name("attributeName").build();
        CategoryAttribute categoryAttribute = CategoryAttribute.builder()
                .id(17L)
                .priority(999L)
                .attributeType(AttributeType.SECOND_TYPE)
                .attribute(attribute)
                .category(category)
                .build();
        CategoryAttributeDtoRequest categoryAttributeDtoRequest = CategoryAttributeDtoRequest.builder()
                .attributeType(AttributeType.FOURTH_TYPE).priority(77L).build();

        when(categoryAttributeRepository.findById(17L)).thenReturn(Optional.of(categoryAttribute));
        when(categoryRepository.findById(19L)).thenReturn(Optional.of(category));
        when(attributeRepository.findById(27L)).thenReturn(Optional.of(attribute));

        categoryAttributesService.updateCategoryAttribute(17L, 19L, 27L, categoryAttributeDtoRequest);

        verify(categoryAttributeRepository).save(captor.capture());
        CategoryAttribute categoryAttributeSaved = captor.getValue();
        assertNotNull(categoryAttributeSaved);
        assertEquals(17L, categoryAttributeSaved.getId());
        assertEquals(77L, categoryAttributeSaved.getPriority());
        assertEquals(AttributeType.FOURTH_TYPE, categoryAttributeSaved.getAttributeType());
        assertEquals(category.getId(), categoryAttributeSaved.getCategory().getId());
        assertEquals(category.getName(), categoryAttributeSaved.getCategory().getName());
        assertEquals(attribute.getId(), categoryAttributeSaved.getAttribute().getId());
        assertEquals(attribute.getName(), categoryAttributeSaved.getAttribute().getName());

        verify(categoryAttributeRepository, atLeast(1)).save(any(CategoryAttribute.class));
        verify(categoryRepository, atLeast(1)).findById(19L);
        verify(attributeRepository, atLeast(1)).findById(27L);
        verify(categoryAttributeRepository, atLeast(1)).findById(17L);
    }

    @Test
    public void updateCategoryAttribute_givenInvalidId_thenExpectNotFound() {
        CategoryAttributeDtoRequest categoryAttributeDtoRequest = CategoryAttributeDtoRequest.builder()
                .attributeType(AttributeType.FOURTH_TYPE).priority(77L).build();
        when(categoryAttributeRepository.findById(17L)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> categoryAttributesService
                .updateCategoryAttribute(17L, 19L, 27L, categoryAttributeDtoRequest));

        verify(categoryAttributeRepository, atLeast(1)).findById(17L);
    }

    @Test
    public void updateCategoryAttribute_givenInvalidCategoryId_thenExpectNotFound() {
        Category category = Category.builder().id(19L).name("CategoryName").build();
        Attribute attribute = Attribute.builder().id(27L).name("attributeName").build();
        CategoryAttribute categoryAttribute = CategoryAttribute.builder()
                .id(17L)
                .priority(999L)
                .attributeType(AttributeType.SECOND_TYPE)
                .attribute(attribute)
                .category(category)
                .build();
        CategoryAttributeDtoRequest categoryAttributeDtoRequest = CategoryAttributeDtoRequest.builder()
                .attributeType(AttributeType.FOURTH_TYPE).priority(77L).build();
        when(categoryAttributeRepository.findById(17L)).thenReturn(Optional.of(categoryAttribute));
        when(categoryRepository.findById(19L)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> categoryAttributesService
                .updateCategoryAttribute(17L, 19L, 27L, categoryAttributeDtoRequest));

        verify(categoryRepository, atLeast(1)).findById(19L);
        verify(categoryAttributeRepository, atLeast(1)).findById(17L);
    }

    @Test
    public void updateCategoryAttribute_givenInvalidAttributeId_thenExpectNotFound() {
        Category category = Category.builder().id(19L).name("CategoryName").build();
        Attribute attribute = Attribute.builder().id(27L).name("attributeName").build();
        CategoryAttribute categoryAttribute = CategoryAttribute.builder()
                .id(17L)
                .priority(999L)
                .attributeType(AttributeType.SECOND_TYPE)
                .attribute(attribute)
                .category(category)
                .build();
        CategoryAttributeDtoRequest categoryAttributeDtoRequest = CategoryAttributeDtoRequest.builder()
                .attributeType(AttributeType.FOURTH_TYPE).priority(77L).build();

        when(categoryAttributeRepository.findById(17L)).thenReturn(Optional.of(categoryAttribute));
        when(categoryRepository.findById(19L)).thenReturn(Optional.of(category));
        when(attributeRepository.findById(27L)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> categoryAttributesService
                .updateCategoryAttribute(17L, 19L, 27L, categoryAttributeDtoRequest));

        verify(categoryRepository, atLeast(1)).findById(19L);
        verify(attributeRepository, atLeast(1)).findById(27L);
        verify(categoryAttributeRepository, atLeast(1)).findById(17L);
    }

    @Test
    public void deleteCategoryAttributeById_givenValidId_expectSuccess() {
        Category category = Category.builder().id(19L).name("CategoryName").build();
        Attribute attribute = Attribute.builder().id(27L).name("attributeName").build();
        CategoryAttribute categoryAttribute = CategoryAttribute.builder()
                .id(17L)
                .priority(999L)
                .attributeType(AttributeType.SECOND_TYPE)
                .attribute(attribute)
                .category(category)
                .build();
        when(categoryAttributeRepository.findById(17L)).thenReturn(Optional.of(categoryAttribute));
        doNothing().when(categoryAttributeRepository).delete(any(CategoryAttribute.class));

        categoryAttributesService.deleteCategoryAttributeById(17L);

        verify(categoryAttributeRepository, atLeast(1)).findById(17L);
        verify(categoryAttributeRepository, atLeast(1)).delete(any(CategoryAttribute.class));
    }

    @Test
    public void deleteCategoryAttributeById_givenInvalidId_expectNotFound() {
        when(categoryAttributeRepository.findById(17L)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> categoryAttributesService.deleteCategoryAttributeById(17L));
        verify(categoryAttributeRepository, atLeast(1)).findById(17L);
    }
}
