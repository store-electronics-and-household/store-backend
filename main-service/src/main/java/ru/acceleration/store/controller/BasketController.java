package ru.acceleration.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.basket.BasketResponseDto;
import ru.acceleration.store.security.model.UserInfo;
import ru.acceleration.store.security.service.UserInfoService;
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

    @PostMapping("/model/{modelId}/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BasketResponseDto> addProductToBasket(@PathVariable Long modelId, Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("POST: /basket/add/{}/user", modelId);
        return ResponseEntity.status(201).body(basketService.addModelToBasket(modelId, userInfo.getId()));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BasketResponseDto> getBasket(Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("GET: /basket/{}", userInfo.getId());
        return ResponseEntity.ok().body(basketService.getBasket(userInfo.getId()));
    }

    @PatchMapping("/model/{modelId}/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BasketResponseDto> removeProductFromBasket(@PathVariable Long modelId, Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("PATCH: /basket/remove/{}", modelId);
        return ResponseEntity.ok().body(basketService.removeModelFromBasket(modelId, userInfo.getId()));
    }
}
