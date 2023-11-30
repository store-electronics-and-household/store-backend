package ru.acceleration.store.dto.attribute;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDtoRequest {

    @NotBlank
    private String name;
}
