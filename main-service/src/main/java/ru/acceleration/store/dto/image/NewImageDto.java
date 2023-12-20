package ru.acceleration.store.dto.image;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewImageDto {

    @NotNull
    @Positive
    private Long modelId;

    @NotBlank
    @Size(max = 200)
    private String imageLink;
}
