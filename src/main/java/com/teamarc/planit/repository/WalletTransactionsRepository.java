package com.teamarc.planit.repository;

import com.teamarc.planit.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletTransactionsRepository extends JpaRepository<WalletTransaction,Long> {
    Optional<WalletTransaction> findByTransactionId(String transactionId);

}
