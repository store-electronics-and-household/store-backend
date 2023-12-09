package ru.acceleration.store.service.attribute;

import ru.acceleration.store.dto.attribute.CategoryAttributeDtoRequest;
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoResponse;

import java.util.List;

public interface CategoryAttributesService {

    CategoryAttributeDtoResponse createCategoryAttributes(Long categoryId, Long attributeId, CategoryAttributeDtoRequest categoryAttributeDtoRequest);

    CategoryAttributeDtoResponse getCategoryAttributeById(Long categoryAttributeId);

    List<CategoryAttributeDtoResponse> getCategoryAttributesByCategoryId(Long categoryId);

    CategoryAttributeDtoResponse updateCategoryAttribute(Long id, Long categoryId, Long attributeId, CategoryAttributeDtoRequest categoryAttributeDtoRequest);

    void deleteCategoryAttributeById(Long Id);
}
