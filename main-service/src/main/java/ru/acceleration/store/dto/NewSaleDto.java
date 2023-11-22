package ru.acceleration.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewSaleDto {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    private String quantity;

    @Positive
    private Long promotionId;
}
