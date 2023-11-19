package ru.acceleration.store.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.acceleration.store.model.ProductInOrder;
import ru.acceleration.store.model.dto.ProductInOrderRequest;
import ru.acceleration.store.model.dto.ProductInOrderResponse;
import ru.acceleration.store.service.OrderService;

@Component
@RequiredArgsConstructor
public class ProductInOrderMapper {
    private final OrderService orderService;

    public ProductInOrder productInOrderRequestToProductInOrder(ProductInOrderRequest productInOrderRequest) {
        ProductInOrder productInOrder = new ProductInOrder();
        //productInOrder.setProduct(ProductService.getProductById(productInOrderRequest.getProductId())); этого еще нет
        productInOrder.setOrder(orderService.getOrderById(productInOrderRequest.getProductId()));

        return productInOrder;
    }

    public ProductInOrderResponse productInOrderToProductInOrderResponse(ProductInOrder productInOrder) {
        ProductInOrderResponse productInOrderResponse = new ProductInOrderResponse();
        productInOrderResponse.setProductInOrderId(productInOrder.getId());
        productInOrderResponse.setProductId(productInOrder.getProduct().getId());
        productInOrderResponse.setOrderId(productInOrder.getOrder().getId());

        return productInOrderResponse;
    }
}