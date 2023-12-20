package ru.acceleration.store.dto.collection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCollectionDto {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    private String imageLink;
}
