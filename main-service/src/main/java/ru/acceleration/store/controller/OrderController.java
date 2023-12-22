package ru.acceleration.store.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.order.OrderRequestDto;
import ru.acceleration.store.dto.order.OrderResponseDto;
import ru.acceleration.store.security.model.UserInfo;
import ru.acceleration.store.security.service.UserInfoService;
import ru.acceleration.store.service.order.OrderService;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/orders")
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000", "https://cyberplace.online", "http://cyberplace.online", "http://45.12.236.120"})
public class OrderController {

    private final UserInfoService userInfoService;

    private final OrderService orderService;

    @PostMapping("/order/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    ResponseEntity<OrderResponseDto> postOrder(@Valid @RequestBody OrderRequestDto orderRequestDto, Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("POST: /orders/order/user");
        return ResponseEntity.status(201).body(orderService.postOrder(orderRequestDto, userInfo.getId()));
    }

    @GetMapping("/order/basket/{basketId}/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    ResponseEntity<OrderResponseDto> getOrder(@PathVariable Long basketId, Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("GET: /orders/order/basket/{}/user", basketId);
        return ResponseEntity.ok().body(orderService.getOrder(userInfo.getId(), basketId));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    ResponseEntity<List<OrderResponseDto>> getOrders(Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        log.info("GET: /orders/{}/user", userInfo.getId());
        return ResponseEntity.ok().body(orderService.getOrders(userInfo.getId()));
    }
}
