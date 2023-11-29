package ru.acceleration.store.dto.collection;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCollectionDto {

    @Size(max = 100)
    private String name;

    private String photoUrl;
}
