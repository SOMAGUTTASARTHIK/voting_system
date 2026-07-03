package com.atlas.votingsystem.repository;

import com.atlas.votingsystem.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findByConstituencyIgnoreCase(String constituency);
}
