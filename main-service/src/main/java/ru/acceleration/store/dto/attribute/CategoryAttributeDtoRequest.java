package ru.acceleration.store.dto.attribute;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.model.enums.AttributeType;
import ru.acceleration.store.model.enums.CategoryType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryAttributeDtoRequest {

    @NotNull
    private Long priority;

    @NotBlank
    private AttributeType attributeType;
}
