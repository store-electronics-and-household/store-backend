package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.NewSaleDto;
import ru.acceleration.store.dto.SaleDto;
import ru.acceleration.store.repository.SaleRepository;

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
}
