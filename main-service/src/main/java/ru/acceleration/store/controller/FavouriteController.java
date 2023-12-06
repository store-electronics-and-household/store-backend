package ru.acceleration.store.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.category.CategoryIncomeDto;
import ru.acceleration.store.dto.category.CategoryOutcomeDto;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.securiry.model.UserInfo;
import ru.acceleration.store.securiry.service.UserInfoService;
import ru.acceleration.store.service.favourite.FavouriteService;
import ru.acceleration.store.validation.OnCreate;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "favourite")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class FavouriteController {

    private final FavouriteService favouriteService;

    private final UserInfoService userInfoService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Добавление товара в избранное", description = "Для авторизованного пользователя")
    public void addFavoriteModel(@RequestBody Long modelId, Principal principal) {
        Long userId = userInfoService.getUserId(principal.getName());
        log.info("FavouriteController addFavoriteModel modelId={}, userId={}", modelId,userId);
        favouriteService.addFavoriteModel(modelId,userId);
    }
}
