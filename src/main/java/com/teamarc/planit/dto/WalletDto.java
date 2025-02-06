package com.teamarc.planit.dto;

import com.teamarc.planit.entity.WalletTransaction;
import lombok.Data;

import java.util.List;

@Data
public class WalletDto {
    private long id;
    private UserDTO user;
    private double balance;
    private List<WalletTransaction> transactions;
}
