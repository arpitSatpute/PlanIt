package com.teamarc.planit.entity;

import com.teamarc.planit.entity.enums.TransactionMethod;
import com.teamarc.planit.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionMethod transactionMethod;
    @ManyToOne
    private Event event;

    private String transactionId;

    @ManyToOne
    private Wallet wallet;

    @CurrentTimestamp
    private LocalDateTime timeStamp;
}
