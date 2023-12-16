package ru.acceleration.store.dto.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewModelDto {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Long price;
}
