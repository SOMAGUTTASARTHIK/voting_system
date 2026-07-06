package com.atlas.votingsystem.repository;

import com.atlas.votingsystem.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoterRepository extends JpaRepository<Voter, Long> {

    Optional<Voter> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);

    boolean existsByAadharNo(String aadharNo);

    boolean existsByAadharNoAndIdNot(String aadharNo, Long id);

    boolean existsByPanNumberIgnoreCase(String panNumber);

    boolean existsByPanNumberIgnoreCaseAndIdNot(String panNumber, Long id);
}
