package ru.acceleration.store.dto.attribute;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotEmpty
    @Size(max = 100, message = "AttributeType must be less than 100 characters")
    private AttributeType attributeType;
}
