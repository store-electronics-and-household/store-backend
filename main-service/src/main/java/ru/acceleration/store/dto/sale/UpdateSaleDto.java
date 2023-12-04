package ru.acceleration.store.dto.sale;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSaleDto {

    @Size(max = 100)
    private String name;

    private String quantity;

    @Positive
    private Long promotionId;
}
