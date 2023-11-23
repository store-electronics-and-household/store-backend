package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.NewProductDto;
import ru.acceleration.store.dto.ProductFullDto;
import ru.acceleration.store.dto.ProductShortDto;
import ru.acceleration.store.model.Product;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    Product toProduct(NewProductDto newProductDto);

    ProductShortDto toProductShortDto(Product product);

    ProductFullDto toProductFullDto(Product product);
}