package ru.acceleration.store.dto.attribute;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelAttributeDtoRequest {

    @NotEmpty
    @Size(max = 250, message = "ModelAttribute value must be less than 250 characters")
    private String value;
}
