package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.sale.NewSaleDto;
import ru.acceleration.store.dto.sale.SaleDto;
import ru.acceleration.store.dto.sale.UpdateSaleDto;
import ru.acceleration.store.service.sale.SaleService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Slf4j
public class SaleController {

    private final SaleService saleService;

    @PostMapping("/{productId}/sale")
    public ResponseEntity<SaleDto> addSale(@PathVariable Long productId, @Valid @RequestBody NewSaleDto newSaleDto) {
        log.info("POST: /product/{}/sale", productId);
        return ResponseEntity.status(201).body(saleService.addSale(productId, newSaleDto));
    }

    @PatchMapping("/{productId}/sale")
    public ResponseEntity<SaleDto> editSale(@PathVariable Long productId, @RequestBody UpdateSaleDto updateSaleDto) {
        log.info("PATCH: /product/{}/sale", productId);
        return ResponseEntity.ok().body(saleService.editSale(productId, updateSaleDto));
    }

    @DeleteMapping("/{productId}/sale")
    public ResponseEntity<Void> deleteSale(@PathVariable Long productId) {
        log.info("DELETE: /product/{}/sale", productId);
        saleService.deleteSale(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
