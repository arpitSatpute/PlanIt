package com.teamarc.planit.repository;

import com.teamarc.planit.entity.OnboardOrganiser;
import com.teamarc.planit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnboardOrganiserRepository extends JpaRepository<OnboardOrganiser, Long> {
    void deleteByUser(User user);
}