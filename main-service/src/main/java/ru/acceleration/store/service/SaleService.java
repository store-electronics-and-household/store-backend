package ru.acceleration.store.service;

import ru.acceleration.store.dto.NewSaleDto;
import ru.acceleration.store.dto.SaleDto;

public interface SaleService {

    SaleDto addSale(Long productId, NewSaleDto newSaleDto);

    SaleDto editSale(Long productId, NewSaleDto newSaleDto);

    void deleteSale(Long productId);
}
