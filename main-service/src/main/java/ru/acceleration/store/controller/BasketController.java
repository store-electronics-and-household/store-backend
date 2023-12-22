package ru.acceleration.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.basket.BasketGetResponseDto;
import ru.acceleration.store.dto.basket.BasketResponseDto;
import ru.acceleration.store.security.model.UserInfo;
import ru.acceleration.store.security.service.UserInfoService;
import ru.acceleration.store.service.basket.BasketService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/basket")
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000", "https://cyberplace.online", "http://cyberplace.online", "http://45.12.236.120"})
public class BasketController {

    private final BasketService basketService;

    private final UserInfoService userInfoService;

    @PostMapping("/model/{modelId}/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BasketResponseDto> addModelToBasket(@PathVariable Long modelId, Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("POST: /basket/model/{}/user", modelId);
        return ResponseEntity.status(201).body(basketService.addModelToBasket(modelId, userInfo.getId()));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BasketGetResponseDto> getBasket(Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("GET: /basket/user/{}", userInfo.getId());
        return ResponseEntity.ok().body(basketService.getBasket(userInfo.getId()));
    }

    @GetMapping("/count/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Long> getBasketGeneralCount(Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("GET: /basket/count/user/{}", userInfo.getId());
        return ResponseEntity.ok().body(basketService.getBasketGeneralCount(userInfo.getId()));
    }

    @PatchMapping("/modelSet/{modelSetId}/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BasketResponseDto> removeModelSetFromBasket(@PathVariable Long modelSetId, Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("PATCH: /basket/modelSet/{}/user", modelSetId);
        return ResponseEntity.ok().body(basketService.removeModelSetFromBasket(modelSetId, userInfo.getId()));
    }

    @PatchMapping("/modelSet/{modelSetId}/count-plus/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BasketResponseDto> plusCountModelSet(@PathVariable Long modelSetId, Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("PATCH: /basket/modelSet/{}/count-plus/user", modelSetId);
        return ResponseEntity.ok().body(basketService.plusCountModelSet(modelSetId, userInfo.getId()));
    }

    @PatchMapping("/modelSet/{modelSetId}/count-minus/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BasketResponseDto> minusCountModelSet(@PathVariable Long modelSetId, Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("PATCH: /basket/modelSet/{}/count-minus/user", modelSetId);
        return ResponseEntity.ok().body(basketService.minusCountModelSet(modelSetId, userInfo.getId()));
    }

    @PatchMapping("model/{modelId}/count-plus/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BasketResponseDto> plusCountModel(@PathVariable Long modelId, Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("PATCH: /basket/model/{}/count-plus/user", modelId);
        return ResponseEntity.ok().body(basketService.plusCountModel(modelId, userInfo.getId()));
    }

    @PatchMapping("model/{modelId}/count-minus/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BasketResponseDto> minusCountModel(@PathVariable Long modelId, Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("PATCH: /basket/model/{}/count-minus/user", modelId);
        return ResponseEntity.ok().body(basketService.minusCountModel(modelId, userInfo.getId()));
    }
}
