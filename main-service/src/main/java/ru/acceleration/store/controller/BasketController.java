package ru.acceleration.store.controller;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.basket.BasketResponseDto;
import ru.acceleration.store.service.basket.BasketService;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "basket")
@Slf4j
public class BasketController {

    private final BasketService basketService;

    @Operation(summary = "Добавление в корзину", description = "Доступ для пользователей")
    @PostMapping("/add/{productId}")
    public ResponseEntity<BasketResponseDto> addProductToBasket(@PathVariable Long productId, @RequestParam Long userId) {
        log.info("POST: /basket/add/{}", productId);
        return ResponseEntity.status(201).body(basketService.addProductToBasket(productId, userId));
    }

    @GetMapping("/{basketId}")
    public ResponseEntity<BasketResponseDto> getBasket(@PathVariable Long basketId) {
        log.info("GET: /basket/{}", basketId);
        return ResponseEntity.ok().body(basketService.getBasket(basketId));
    }

    @PatchMapping("/remove/{productId}")
    public ResponseEntity<BasketResponseDto> removeProductFromBasket(@PathVariable Long productId, @RequestParam Long basketId) {
        log.info("PATCH: /basket/remove/{}", productId);
        return ResponseEntity.ok().body(basketService.removeProductFromBasket(productId, basketId));
    }
}
