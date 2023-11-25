package ru.acceleration.store.dto.attribute;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class CategoryAttributesDto {

    String attributeName;

    Set<String> listvalue;
}
