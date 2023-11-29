package ru.acceleration.store.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: описать все необходимые поля для товара
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelFullDto {

    private Long id;

    private String vendorCode;

    private String name;

    private String price;

    // и т.д.
}
