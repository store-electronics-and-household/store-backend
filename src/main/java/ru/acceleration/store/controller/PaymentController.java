package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.mapper.PaymentMapper;
import ru.acceleration.store.model.dto.PaymentRequest;
import ru.acceleration.store.model.dto.PaymentResponse;
import ru.acceleration.store.service.PaymentService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/payment")
@Validated
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse createPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        log.info("Controller layer: POST /payment request for user with id: {} obtained.", paymentRequest.getUserId());

        return paymentMapper.paymentToPaymentResponse(paymentService.createPayment(paymentMapper
                .paymentRequestToPayment(paymentRequest)));
    }

    @DeleteMapping("/{paymentId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePayment(@PathVariable Long paymentId) {
        log.info("Controller layer: DELETE /payment/{paymentId}/delete request for payment with id: {} obtained.",
                paymentId);

        paymentService.deletePaymentById(paymentId);
    }

    @GetMapping("/{paymentId}")
    public PaymentResponse getPaymentById(@PathVariable Long paymentId) {
        log.info("Controller layer: GET /payment/{paymentId} request for payment with id: {} obtained.", paymentId);

        return paymentMapper.paymentToPaymentResponse(paymentService.getPaymentById(paymentId));
    }
}