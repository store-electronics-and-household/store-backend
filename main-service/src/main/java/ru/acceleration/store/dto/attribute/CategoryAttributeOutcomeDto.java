package ru.acceleration.store.dto.attribute;

import jakarta.persistence.*;
import lombok.*;
import ru.acceleration.store.dto.category.CategoryOutcomeDto;
import ru.acceleration.store.model.Attribute;
import ru.acceleration.store.model.Category;
import ru.acceleration.store.model.enums.AttributeType;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryAttributeOutcomeDto {

    private Long id;

    private CategoryOutcomeDto categoryOutcomeDto;

    private AttributeOutcomeDto attributeOutcomeDto;

    private Long priority;

    private AttributeType attributeType;
}
