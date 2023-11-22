package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.NewSaleDto;
import ru.acceleration.store.dto.SaleDto;
import ru.acceleration.store.service.SaleService;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class SaleController {

    private final SaleService saleService;

    @PostMapping("/product/{productId}/sale")
    @ResponseStatus(HttpStatus.CREATED)
    public SaleDto addSale(@PathVariable Long productId, @Valid @RequestBody NewSaleDto newSaleDto) {
        log.info("POST: /product/{}/sale", productId);
        return saleService.addSale(productId, newSaleDto);
    }

    @PatchMapping("/product/{productId}/sale")
    public SaleDto editSale(@PathVariable Long productId, @RequestBody NewSaleDto newSaleDto) {
        log.info("PATCH: /product/{}/sale", productId);
        return saleService.editSale(productId, newSaleDto);
    }

    @DeleteMapping("/product/{productId}/sale")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSale(@PathVariable Long productId) {
        log.info("DELETE: /product/{}/sale", productId);
        saleService.deleteSale(productId);
    }
}
