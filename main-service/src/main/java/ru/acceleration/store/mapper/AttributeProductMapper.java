package ru.acceleration.store.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.acceleration.store.dto.AttributeCategoryResponse;
import ru.acceleration.store.dto.CategoryAttributesDto;
import ru.acceleration.store.dto.ProductAttributesDto;
import ru.acceleration.store.model.ProductAttributes;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AttributeProductMapper {

    public static ProductAttributesDto toProductAttributesDto(ProductAttributes productAttributes) {
        return ProductAttributesDto.builder()
                .attributeName(productAttributes.getAttribute().getName())
                .value(productAttributes.getValue())
                .build();
    }

    public static AttributeCategoryResponse toAttributeCategoryResponse(List<ProductAttributes> productAttributesList) {
        Map<String, Set<String>> mapAttribuiteCategory = new HashMap<>();
        for (ProductAttributes productAttributes : productAttributesList) {
            if (!(mapAttribuiteCategory.containsKey(productAttributes.getAttribute().getName()))) {
                mapAttribuiteCategory.put(productAttributes.getAttribute().getName(), new HashSet<>());
            }
            mapAttribuiteCategory.get(productAttributes.getAttribute().getName()).add(productAttributes.getValue());
        }
        List<CategoryAttributesDto> categoryAttributesDtoList = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : mapAttribuiteCategory.entrySet()) {
            categoryAttributesDtoList.add(new CategoryAttributesDto(entry.getKey(), entry.getValue()));
        }
        return new AttributeCategoryResponse(categoryAttributesDtoList);
    }
}
