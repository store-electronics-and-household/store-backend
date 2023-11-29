package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.sale.NewSaleDto;
import ru.acceleration.store.dto.sale.SaleDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SaleMapper {

    Sale toSale(NewSaleDto newSaleDto);

    Sale toSale(SaleDto saleDto);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "promotion.id", target = "promotionId")
    SaleDto toSaleDto(Sale sale);
}
