package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.NewSaleDto;
import ru.acceleration.store.dto.SaleDto;
import ru.acceleration.store.model.Sale;
import ru.acceleration.store.repository.SaleRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    @Override
    public SaleDto addSale(Long productId, NewSaleDto newSaleDto) {
        return null;
    }

    @Override
    public SaleDto editSale(Long productId, NewSaleDto newSaleDto) {
        return null;
    }

    @Override
    public void deleteSale(Long productId) {

    }

    @Override
    public List<Sale> getSalesByPromotion(Long promotionId) {
        return saleRepository.getSalesByPromotion(promotionId);
    }

    @Override
    public Sale saveSaleToDatabase(Sale sale) {
        return saleRepository.save(sale);
    }
}
