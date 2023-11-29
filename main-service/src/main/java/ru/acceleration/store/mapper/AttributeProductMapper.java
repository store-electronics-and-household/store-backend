package ru.acceleration.store.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.acceleration.store.dto.attribute.AttributeCategoryResponse;
import ru.acceleration.store.dto.attribute.CategoryAttributeOutcomeDto;
import ru.acceleration.store.dto.attribute.ProductAttributesDto;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AttributeProductMapper {

    public static ProductAttributesDto toProductAttributesDto(ProductAttributes productAttributes) {
        return ProductAttributesDto.builder()
                .attributeName(productAttributes.getAttribute().getName())
                .value(productAttributes.getValue())
                .build();
    }

    // Преобразование списка всех уникальных атрибутов продукта, относящегося к одной категории (List<ProductAttributes>) к списку,
    // внутри которого название атрибута и список всех уникальных атрибутов продукта, относящегося к одной категории (List<CategoryAttributesDto>)
    public static AttributeCategoryResponse toAttributeCategoryResponse(List<ProductAttributes> productAttributesList) {
        Map<String, Set<String>> mapAttribuiteCategory = new HashMap<>();
        for (ProductAttributes productAttributes : productAttributesList) {
            if (!(mapAttribuiteCategory.containsKey(productAttributes.getAttribute().getName()))) {
                mapAttribuiteCategory.put(productAttributes.getAttribute().getName(), new HashSet<>());
            }
            mapAttribuiteCategory.get(productAttributes.getAttribute().getName()).add(productAttributes.getValue());
        }
        List<CategoryAttributeOutcomeDto> categoryAttributeOutcomeDtoList = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : mapAttribuiteCategory.entrySet()) {
            categoryAttributeOutcomeDtoList.add(new CategoryAttributeOutcomeDto(entry.getKey(), entry.getValue()));
        }
        return new AttributeCategoryResponse(categoryAttributeOutcomeDtoList);
    }
}
