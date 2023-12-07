package ru.acceleration.store.service.sale;

import ru.acceleration.store.dto.sale.NewSaleDto;
import ru.acceleration.store.dto.sale.SaleDto;
import ru.acceleration.store.dto.sale.UpdateSaleDto;
import ru.acceleration.store.model.Sale;

public interface SaleService {

    SaleDto addSale(NewSaleDto newSaleDto);

    SaleDto editSale(Long saleId, UpdateSaleDto updateSaleDto);

    void deleteSale(Long saleId);

    Sale getExistingSale(Long saleId);
}
