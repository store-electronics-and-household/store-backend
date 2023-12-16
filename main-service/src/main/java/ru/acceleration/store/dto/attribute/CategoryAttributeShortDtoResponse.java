package ru.acceleration.store.dto.attribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAttributeShortDtoResponse {

    private Long id;

    private Long priority;

    private String attributeName;
}
