package com.atlas.votingsystem.repository;

import com.atlas.votingsystem.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findByConstituencyIgnoreCase(String constituency);

    List<Candidate> findAllByOrderByVoteCountDesc();

    List<Candidate> findByConstituencyIgnoreCaseOrderByVoteCountDesc(String constituency);

    boolean existsByPartyNameIgnoreCase(String partyName);

    boolean existsByPartyNameIgnoreCaseAndIdNot(String partyName, Long id);

    @Modifying
    @Query("update Candidate c set c.voteCount = c.voteCount + 1 where c.id = :id")
    int incrementVoteCount(@Param("id") Long id);
}
