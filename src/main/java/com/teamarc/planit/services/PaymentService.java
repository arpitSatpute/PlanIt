package com.teamarc.planit.services;


import com.teamarc.planit.entity.Payment;
import com.teamarc.planit.entity.enums.PaymentStatus;
import com.teamarc.planit.exceptions.ResourceNotFoundException;
import com.teamarc.planit.repository.PaymentRepository;
import com.teamarc.planit.strategies.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService{
    private final WalletPaymentStrategy walletPaymentStrategy;
    private final PaymentRepository paymentRepository;

    public void processPayment(Event event) {
        Payment payment=paymentRepository.findByEvent(event)
                .orElseThrow(()-> new ResourceNotFoundException("Payment not found for ride with id: "+event.getId()));
        walletPaymentStrategy.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    public Payment createNewPayment(Event event) {
        Payment payment= Payment.builder()
                .event(event)
                .paymentStatus(PaymentStatus.PENDING)
                .amount(event.getFare())
                .build();
        return paymentRepository.save(payment);
    }

    public void updatePaymentStatus(Payment payment, PaymentStatus status) {
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
    }
}
