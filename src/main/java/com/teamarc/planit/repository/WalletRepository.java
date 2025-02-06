package com.teamarc.planit.repository;


import com.teamarc.planit.entity.User;
import com.teamarc.planit.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {

    Optional<Wallet> findByUser(User user);
    Optional<Wallet> findByUserId(long id);
}
