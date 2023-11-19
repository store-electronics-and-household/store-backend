package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.model.Payment;
import ru.acceleration.store.repository.PaymentRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Transactional
    @Override
    public Payment createPayment(Payment payment) {
        log.info("Service layer: POST /payment request for user with id: {} obtained.", payment.getUser().getId());

        return paymentRepository.save(payment);
    }

    @Transactional
    @Override
    public void deletePaymentById(Long paymentId) {
        log.info("Service layer: DELETE /payment/{paymentId}/delete request for payment with id: {} obtained.",
                paymentId);

        try {
            paymentRepository.deleteById(paymentId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("Payment with id: " + paymentId + " doesn't exist in database.");
        }
    }

    @Override
    public Payment getPaymentById(Long paymentId) {
        log.info("Service layer: GET payment/{paymentId} request for payment with id: {} obtained.", paymentId);

        return paymentRepository.findById(paymentId).orElseThrow(() ->
                new DataNotFoundException("Payment with id: " + paymentId + " doesn't exist in database."));
    }
}