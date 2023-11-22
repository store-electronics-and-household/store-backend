package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.NewSaleDto;
import ru.acceleration.store.dto.SaleDto;
import ru.acceleration.store.model.Sale;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SaleMapper {

    SaleDto toSaleDto(NewSaleDto newSaleDto);

    SaleDto toSaleDto(Sale sale);

    Sale toSale(NewSaleDto newSaleDto);

    Sale toSale(SaleDto saleDto);
}
