package ru.acceleration.store.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.favourite.FavouriteResponseDto;
import ru.acceleration.store.mapper.ModelMapper;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.security.service.UserInfoService;
import ru.acceleration.store.service.user.UserService;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/favourite")
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000", "https://cyberplace.online", "http://cyberplace.online", "http://45.12.236.120"})
public class FavouriteController {

    private final UserInfoService userInfoService;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Добавление товара в избранное", description = "Для авторизованного пользователя")
    public void addFavoriteModel(@RequestParam long modelId, Principal principal) {
        Long userId = userInfoService.getUserId(principal.getName());
        log.info("FavouriteController addFavoriteModel modelId={}, userId={}", modelId, userId);
        userService.addFavoriteModel(modelId, userId);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Удаление товара из избранного", description = "Для авторизованного пользователя")
    public void deleteFavoriteModel(@RequestParam long modelId, Principal principal) {
        Long userId = userInfoService.getUserId(principal.getName());
        log.info("FavouriteController deleteFavoriteModel modelId={}, userId={}", modelId, userId);
        userService.deleteFavoriteModel(modelId, userId);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Список всех товаров из избранного пользователя", description = "Для авторизованного пользователя")
    public FavouriteResponseDto getAllFavorite(Principal principal) {
        Long userId = userInfoService.getUserId(principal.getName());
        log.info("FavouriteController getAllFavorite userId={}", userId);
        Set<Model> models = userService.getAllFavorite(userId);
        FavouriteResponseDto favouriteResponseDto = new FavouriteResponseDto(models.stream().map(modelMapper::toModelShortDto).collect(Collectors.toSet()));
        return favouriteResponseDto;
    }
}
