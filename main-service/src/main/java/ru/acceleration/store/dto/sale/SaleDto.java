package ru.acceleration.store.dto.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDto {

    private Long id;

    private String name;

    private String quantity;

    private Long productId;

    private Long promotionId;
}
