package ru.acceleration.store.dto.attribute;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class ProductAttributesDto {

    String attributeName;

    String value;
}