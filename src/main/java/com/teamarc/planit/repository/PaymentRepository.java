package com.teamarc.planit.repository;


import com.teamarc.planit.entity.Event;
import com.teamarc.planit.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByEvent(Event event);
}
