package ru.acceleration.store.dto.basket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.dto.product.ProductFullDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketResponseDto {

    private Long id;

    private Long userId;

    private List<ProductFullDto> products;
}