package com.teamarc.planit.dto;


import com.teamarc.planit.entity.enums.TransactionMethod;
import com.teamarc.planit.entity.enums.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WalletTransactionDto {
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;

    private String transactionId;
    private WalletDto wallet;
    private LocalDateTime timeStamp;
}
