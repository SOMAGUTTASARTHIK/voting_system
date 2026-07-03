package com.atlas.votingsystem.service;

import com.atlas.votingsystem.dto.CandidateRequest;
import com.atlas.votingsystem.entity.Candidate;
import com.atlas.votingsystem.exception.ResourceNotFoundException;
import com.atlas.votingsystem.repository.CandidateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public List<Candidate> getCandidatesByConstituency(String constituency) {
        return candidateRepository.findByConstituencyIgnoreCase(constituency);
    }

    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id: " + id));
    }

    public Candidate createCandidate(CandidateRequest request) {
        Candidate candidate = new Candidate();
        copyRequestToCandidate(request, candidate);
        return candidateRepository.save(candidate);
    }

    @Transactional
    public Candidate updateCandidate(Long id, CandidateRequest request) {
        Candidate candidate = getCandidateById(id);
        copyRequestToCandidate(request, candidate);
        return candidate;
    }

    public void deleteCandidate(Long id) {
        Candidate candidate = getCandidateById(id);
        candidateRepository.delete(candidate);
    }

    @Transactional
    public Candidate addVote(Long id) {
        Candidate candidate = getCandidateById(id);
        candidate.setVoteCount(candidate.getVoteCount() + 1);
        return candidate;
    }

    private void copyRequestToCandidate(CandidateRequest request, Candidate candidate) {
        candidate.setName(request.getName());
        candidate.setPartyName(request.getPartyName());
        candidate.setConstituency(request.getConstituency());
        candidate.setManifesto(request.getManifesto());
    }
}
