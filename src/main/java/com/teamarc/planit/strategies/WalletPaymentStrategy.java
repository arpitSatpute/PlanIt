package com.teamarc.planit.strategies;



import com.teamarc.planit.entity.*;
import com.teamarc.planit.entity.enums.PaymentStatus;
import com.teamarc.planit.entity.enums.Role;
import com.teamarc.planit.repository.PaymentRepository;
import com.teamarc.planit.services.UserService;
import com.teamarc.planit.services.WalletService;
import com.teamarc.planit.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class WalletPaymentStrategy {

    private final BigDecimal PLATFORM_COMMISSION = new BigDecimal("0.1");
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;
    private final WalletTransactionService walletTransactionService;
    private final UserService userService;

    @Transactional
    public void processPayment(Payment payment,Long participantId) {

        Host host = payment.getEvent().getHost();
        Participant participant = payment.getEvent().getParticipants()
                .stream()
                .filter(p -> p.getId().equals(participantId))
                .findFirst().orElseThrow(() -> new RuntimeException("Participant not found"));
        Wallet participantWallet = walletService.findWalletById(participant.getId());

        if (participantWallet.getBalance().compareTo(payment.getAmount()) < 0) {
            payment.setPaymentStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
            throw new RuntimeException("Insufficient balance in wallet: payment failed");
        }

        walletService.deductMoneyToWallet(participant.getUser(), payment.getAmount(), generateTransactionId(), payment.getEvent());

        BigDecimal hostCut = payment.getAmount().multiply(BigDecimal.ONE.subtract(PLATFORM_COMMISSION));
        BigDecimal platformCut = payment.getAmount().subtract(hostCut);
        User admin = userService.loadUserByRole(Role.ADMIN);
        walletService.addMoneyToWallet(admin, platformCut, generateTransactionId(), payment.getEvent());
        walletService.addMoneyToWallet(host.getUser(), hostCut, generateTransactionId(), payment.getEvent());
        payment.setPaymentStatus(PaymentStatus.COMPLETED);
        paymentRepository.save(payment);
    }

    @Transactional
    public void refundPayment(Payment payment, Long participantId) {
        Host host = payment.getEvent().getHost();
        //TODO
        Participant participant = payment.getEvent().getParticipants()
                .stream()
                .filter(p -> p.getId().equals(participantId))
                .findFirst().orElseThrow(() -> new RuntimeException("Participant not found"));

        Wallet hostWallet = walletService.findWalletById(host.getId());

        if (hostWallet.getBalance().compareTo(payment.getAmount()) < 0) {
            payment.setPaymentStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
            throw new RuntimeException("Insufficient balance in mentor's wallet: refund failed");
        }

        walletService.addMoneyToWallet(participant.getUser(), payment.getAmount(), generateTransactionId(), payment.getEvent());
        walletService.deductMoneyToWallet(host.getUser(), payment.getAmount(), generateTransactionId(), payment.getEvent());
        payment.setPaymentStatus(PaymentStatus.REFUNDED);
        paymentRepository.save(payment);
    }

    public String generateTransactionId() {
        String transactionId = "TX" + UUID.randomUUID().toString().replace("-", "");
        if (walletTransactionService.findByTransactionId(transactionId).isPresent()) {
            return generateTransactionId();
        }
        return transactionId;
    }
}
