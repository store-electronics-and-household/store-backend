package ru.acceleration.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: описать все необходимые поля для карточки товара, отображающейся в каталоге
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductShortDto {

    private Long id;

    private String vendorCode;

    private String name;

    private String price;

//    private List<Image> images;
}
