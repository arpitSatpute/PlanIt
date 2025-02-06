package com.teamarc.planit.repository;

import com.teamarc.planit.entity.RequestOrganiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestOrganiserRepository extends JpaRepository<RequestOrganiser, Long> {
}
