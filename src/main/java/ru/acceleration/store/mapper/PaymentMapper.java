package ru.acceleration.store.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.acceleration.store.model.Payment;
import ru.acceleration.store.model.dto.PaymentRequest;
import ru.acceleration.store.model.dto.PaymentResponse;
import ru.acceleration.store.service.OrderService;
import ru.acceleration.store.service.UserService;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PaymentMapper {
    private final OrderService orderService;
    private final UserService userService;
    //private final BankCardService bankCardService; этого еще нет

    public Payment paymentRequestToPayment(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setOrder(orderService.getOrderById(paymentRequest.getOrderId()));
        payment.setUser(userService.getUserName(paymentRequest.getUserId()));
        //payment.setBankCard(bankCardService.getBankCardById(paymentRequest.getBankCardId())); этого еще нет
        payment.setStatus(paymentRequest.getStatus());
        payment.setCreated(LocalDateTime.now());

        return payment;
    }

    public PaymentResponse paymentToPaymentResponse(Payment payment) {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPaymentId(payment.getId());
        paymentResponse.setOrderId(payment.getOrder().getId());
        paymentResponse.setUserId(payment.getUser().getId());
        paymentResponse.setBankCardId(payment.getBankCard().getId());
        paymentResponse.setStatus(payment.getStatus());
        paymentResponse.setCreated(payment.getCreated());

        return paymentResponse;
    }
}