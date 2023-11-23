package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.ProductAttributesDto;
import ru.acceleration.store.model.ProductAttributes;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductAttributesMapper {

    ProductAttributesDto toProductAttributesDto(ProductAttributes productAttributes);

    List<ProductAttributesDto> toListProductAttributesDto(List<ProductAttributes> productAttributesList);
}

