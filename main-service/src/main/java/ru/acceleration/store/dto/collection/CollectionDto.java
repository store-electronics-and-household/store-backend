package ru.acceleration.store.dto.collection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDto {

    /**
     * Идентификатор подборки
     */
    private Long id;

    /**
     * Название подборки с акциями
     */
    @NotBlank
    @Size(max = 100)
    private String name;

    /**
     * Ссылка на фотографию баннера
     */
    @NotBlank
    private String imageLink;
}
