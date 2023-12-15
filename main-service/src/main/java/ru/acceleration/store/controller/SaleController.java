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
@RequestMapping("/sale")
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000", "https://cyberplace.online"})
public class SaleController {

    private final SaleService saleService;

    @PatchMapping("/{saleId}")
    public ResponseEntity<SaleDto> editSale(@PathVariable Long saleId, @Valid @RequestBody UpdateSaleDto updateSaleDto) {
        log.info("PATCH: /sale/{}", saleId);
        return ResponseEntity.ok().body(saleService.editSale(saleId, updateSaleDto));
    }

    @PostMapping
    public ResponseEntity<SaleDto> addSale(@Valid @RequestBody NewSaleDto newSaleDto) {
        log.info("POST: /sale");
        return ResponseEntity.status(201).body(saleService.addSale(newSaleDto));
    }

    @DeleteMapping("/{saleId}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long saleId) {
        log.info("DELETE: /sale/{}", saleId);
        saleService.deleteSale(saleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
