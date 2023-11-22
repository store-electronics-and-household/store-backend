package ru.acceleration.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDto {

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
}
