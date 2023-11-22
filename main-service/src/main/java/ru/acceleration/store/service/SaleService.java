package ru.acceleration.store.service;

import ru.acceleration.store.dto.NewSaleDto;
import ru.acceleration.store.dto.SaleDto;
import ru.acceleration.store.model.Sale;

import java.util.List;

public interface SaleService {

    SaleDto addSale(Long productId, NewSaleDto newSaleDto);

    SaleDto editSale(Long productId, NewSaleDto newSaleDto);

    void deleteSale(Long productId);

    List<Sale> getSalesByPromotion(Long promotionId);

    Sale saveSaleToDatabase(Sale sale);
}
