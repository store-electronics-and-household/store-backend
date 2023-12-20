package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.order.OrderRequestDto;
import ru.acceleration.store.dto.order.OrderResponseDto;
import ru.acceleration.store.security.model.UserInfo;
import ru.acceleration.store.security.service.UserInfoService;
import ru.acceleration.store.service.order.OrderService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/order")
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000", "https://cyberplace.online", "http://cyberplace.online", "http://45.12.236.120"})
public class OrderController {

    private final UserInfoService userInfoService;
    private final OrderService orderService;

    @PostMapping("/user")
    ResponseEntity<OrderResponseDto> postOrder(@Valid @RequestBody OrderRequestDto orderRequestDto, Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("POST: /order/user");
        return ResponseEntity.status(201).body(orderService.postOrder(orderRequestDto, userInfo.getId()));
    }

    @GetMapping("/user")
    ResponseEntity<OrderResponseDto> getOrder(Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("GET: /order/user/{}", userInfo.getId());
        return ResponseEntity.ok().body(orderService.getOrder(userInfo.getId()));
    }
}
