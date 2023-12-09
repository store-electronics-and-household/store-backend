package ru.acceleration.store.service.attribute;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoRequest;
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoResponse;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.CategoryAttributeMapper;
import ru.acceleration.store.model.Attribute;
import ru.acceleration.store.model.Category;
import ru.acceleration.store.model.CategoryAttribute;
import ru.acceleration.store.repository.AttributeRepository;
import ru.acceleration.store.repository.CategoryAttributeRepository;
import ru.acceleration.store.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryAttributesServiceImpl implements CategoryAttributesService {

    CategoryAttributeRepository categoryAttributeRepository;

    CategoryRepository categoryRepository;

    CategoryAttributeMapper categoryAttributeMapper;

    AttributeRepository attributeRepository;

    @Override
    public CategoryAttributeDtoResponse createCategoryAttributes(
            Long categoryId, Long attributeId, CategoryAttributeDtoRequest categoryAttributeDtoRequest) {
        Category category = validateCategoryById(categoryId);
        Attribute attribute = validateAttributeById(attributeId);
        CategoryAttribute categoryAttribute = CategoryAttribute.builder()
                .category(category)
                .attribute(attribute)
                .priority(categoryAttributeDtoRequest.getPriority())
                .attributeType(categoryAttributeDtoRequest.getAttributeType())
                .build();
        CategoryAttribute categoryAttributeSaved = categoryAttributeRepository.save(categoryAttribute);
        return categoryAttributeMapper.toCategoryAttributeDtoResponse(categoryAttributeSaved);
    }

    @Override
    public CategoryAttributeDtoResponse getCategoryAttributeById(Long categoryAttributeId) {
        CategoryAttribute categoryAttribute = validateCategoryAttributeById(categoryAttributeId);
        return categoryAttributeMapper.toCategoryAttributeDtoResponse(categoryAttribute);
    }

    @Override
    public List<CategoryAttributeDtoResponse> getCategoryAttributesByCategoryId(Long categoryId) {
        validateCategoryById(categoryId);
        return categoryAttributeRepository.findAllCategoryAttributeByCategoryIdOrderByPriorityDesc(categoryId).stream()
                .map(categoryAttributeMapper::toCategoryAttributeDtoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryAttributeDtoResponse updateCategoryAttribute(
            Long id, Long categoryId, Long attributeId, CategoryAttributeDtoRequest categoryAttributeDtoRequest) {
        CategoryAttribute categoryAttribute = validateCategoryAttributeById(id);
        Category category = validateCategoryById(categoryId);
        Attribute attribute = validateAttributeById(attributeId);
        categoryAttribute.setCategory(category);
        categoryAttribute.setAttribute(attribute);
        categoryAttribute.setPriority(categoryAttributeDtoRequest.getPriority());
        categoryAttribute.setAttributeType(categoryAttributeDtoRequest.getAttributeType());
        CategoryAttribute categoryAttributeSaved = categoryAttributeRepository.save(categoryAttribute);
        return categoryAttributeMapper.toCategoryAttributeDtoResponse(categoryAttributeSaved);
    }

    @Override
    public void deleteCategoryAttributeById(Long id) {
        CategoryAttribute categoryAttribute = validateCategoryAttributeById(id);
        categoryAttributeRepository.delete(categoryAttribute);
    }

    private Category validateCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new DataNotFoundException(String.format("Category id=%s not found", categoryId)));
    }

    private CategoryAttribute validateCategoryAttributeById(Long categoryAttributeId) {
        return categoryAttributeRepository.findById(categoryAttributeId)
                .orElseThrow(() -> new DataNotFoundException(String.format("CategoryAttribute id=%s not found", categoryAttributeId)));
    }

    private Attribute validateAttributeById(Long attributeId) {
        return attributeRepository.findById(attributeId)
                .orElseThrow(() -> new DataNotFoundException(String.format("Attribute id=%s not found", attributeId)));
    }
}
