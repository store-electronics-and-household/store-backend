package ru.acceleration.store.service.sale;

import ru.acceleration.store.dto.sale.NewSaleDto;
import ru.acceleration.store.dto.sale.SaleDto;
import ru.acceleration.store.dto.sale.UpdateSaleDto;

public interface SaleService {

    SaleDto addSale(Long productId, NewSaleDto newSaleDto);

    SaleDto editSale(Long productId, UpdateSaleDto updateSaleDto);

    void deleteSale(Long productId);
}
