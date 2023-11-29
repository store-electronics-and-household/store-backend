package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.product.NewProductDto;
import ru.acceleration.store.dto.product.ProductFullDto;
import ru.acceleration.store.dto.product.ProductShortDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    Product toProduct(NewProductDto newProductDto);

    ProductShortDto toProductShortDto(Product product);

    ProductFullDto toProductFullDto(Product product);
}