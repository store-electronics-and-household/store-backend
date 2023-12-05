package ru.acceleration.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.basket.BasketResponseDto;
import ru.acceleration.store.securiry.model.UserInfo;
import ru.acceleration.store.securiry.service.UserInfoService;
import ru.acceleration.store.service.basket.BasketService;

import java.security.Principal;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "basket")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class BasketController {

    private final BasketService basketService;

    private final UserInfoService userInfoService;

    @PostMapping("/add/{productId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BasketResponseDto> addProductToBasket(@PathVariable Long productId, Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("POST: /basket/add/{}", productId);
        return ResponseEntity.status(201).body(basketService.addProductToBasket(productId, userInfo.getId()));
    }

    @GetMapping("/{basketId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BasketResponseDto> getBasket(@PathVariable Long basketId) {
        log.info("GET: /basket/{}", basketId);
        return ResponseEntity.ok().body(basketService.getBasket(basketId));
    }

    @PatchMapping("/remove/{productId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BasketResponseDto> removeProductFromBasket(@PathVariable Long productId, @RequestParam Long basketId) {
        log.info("PATCH: /basket/remove/{}", productId);
        return ResponseEntity.ok().body(basketService.removeProductFromBasket(productId, basketId));
    }
}
