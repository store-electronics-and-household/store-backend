package ru.acceleration.store.dto.attribute;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.model.enums.AttributeType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryAttributeDtoRequest {

    @NotNull
    private Long priority;

    @NotNull
    private AttributeType attributeType;
}
