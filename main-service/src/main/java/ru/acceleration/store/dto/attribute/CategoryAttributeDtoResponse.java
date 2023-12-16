package ru.acceleration.store.dto.attribute;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.dto.category.CategoryOutcomeDto;
import ru.acceleration.store.model.enums.AttributeType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryAttributeDtoResponse {

    private Long id;

    private Long priority;

    private CategoryOutcomeDto categoryOutcomeDto;

    private AttributeDtoResponse attributeDtoResponse;

    private AttributeType attributeType;
}
