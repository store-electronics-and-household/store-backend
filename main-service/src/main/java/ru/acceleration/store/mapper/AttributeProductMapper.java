package ru.acceleration.store.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.acceleration.store.dto.ProductAttributesDto;
import ru.acceleration.store.model.ProductAttributes;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AttributeProductMapper {

    public static ProductAttributesDto toproductAttributesDto(ProductAttributes productAttributes) {
        return ProductAttributesDto.builder()
                .attributeName(productAttributes.getAttribute().getName())
                .value(productAttributes.getValue())
                .build();
    }

}
