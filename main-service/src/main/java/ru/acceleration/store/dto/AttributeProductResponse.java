package ru.acceleration.store.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AttributeProductResponse {

    List<ProductAttributesDto> attributes;
}
