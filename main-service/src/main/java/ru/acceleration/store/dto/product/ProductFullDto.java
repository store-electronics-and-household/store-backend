package ru.acceleration.store.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: описать все необходимые поля для товара
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFullDto {

    private Long id;

    private String vendorCode;

    private String name;

    private String price;

    // и т.д.
}
