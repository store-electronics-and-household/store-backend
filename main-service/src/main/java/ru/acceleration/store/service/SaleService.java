package ru.acceleration.store.service;

import ru.acceleration.store.dto.NewSaleDto;
import ru.acceleration.store.dto.SaleDto;
import ru.acceleration.store.dto.UpdateSaleDto;

public interface SaleService {

    SaleDto addSale(Long productId, NewSaleDto newSaleDto);

    SaleDto editSale(Long productId, UpdateSaleDto updateSaleDto);

    void deleteSale(Long productId);
}
