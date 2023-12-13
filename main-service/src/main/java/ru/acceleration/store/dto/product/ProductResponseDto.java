package ru.acceleration.store.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.model.Model;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private Long id;

    private String serialNumber;

    private Model model;
}
