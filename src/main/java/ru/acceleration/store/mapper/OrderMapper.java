package ru.acceleration.store.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.acceleration.store.model.Order;
import ru.acceleration.store.model.dto.OrderRequest;
import ru.acceleration.store.model.dto.OrderResponse;
import ru.acceleration.store.service.UserService;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final UserService userService;

    public Order orderRequestToOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setUser(userService.getUserName(orderRequest.getUserId()));
        //order.setProduct(productService.getProductById(orderRequest.getProductId())); этого еще нет
        order.setCreated(LocalDateTime.now());
        order.setSum(orderRequest.getSum());
        //order.setSale(saleService.getSaleById(orderRequest.getSalesId())); этого еще нет
        order.setStatus(orderRequest.getStatus());

        return order;
    }

    public OrderResponse orderToOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getId());
        orderResponse.setUserId(order.getUser().getId());
        orderResponse.setProductId(order.getProduct().getId());
        orderResponse.setCreated(order.getCreated());
        orderResponse.setSum(order.getSum());
        orderResponse.setSalesId(order.getSale().getId());
        orderResponse.setStatus(order.getStatus());

        return orderResponse;
    }
}