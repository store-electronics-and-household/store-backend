package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.acceleration.store.dto.product.ProductResponseDto;
import ru.acceleration.store.model.Product;

@Mapper(componentModel = "spring", uses = {ModelMapper.class})
public interface ProductMapper {

    @Mapping(source = "model", target = "model")
    ProductResponseDto toProductResponseDto(Product product);
}
