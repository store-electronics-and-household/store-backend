package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.sale.NewSaleDto;
import ru.acceleration.store.dto.sale.SaleDto;
import ru.acceleration.store.model.Sale;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SaleMapper {

    Sale toSale(NewSaleDto newSaleDto);

    Sale toSale(SaleDto saleDto);

    @Mapping(source = "id", target = "modelId")
    SaleDto toSaleDto(Sale sale);
}
