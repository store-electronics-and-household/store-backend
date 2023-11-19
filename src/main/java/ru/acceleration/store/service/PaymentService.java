package ru.acceleration.store.service;

import ru.acceleration.store.model.Payment;

public interface PaymentService {
    Payment createPayment(Payment payment);

    void deletePaymentById(Long paymentId);

    Payment getPaymentById(Long paymentId);
}