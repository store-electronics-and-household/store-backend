package ru.acceleration.store.dto.attribute;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AttributeCategoryRequest {

    long categoryId;
}