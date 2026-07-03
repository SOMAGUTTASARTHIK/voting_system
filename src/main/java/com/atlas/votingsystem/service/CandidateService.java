package com.atlas.votingsystem.service;

import com.atlas.votingsystem.dto.CandidateRequest;
import com.atlas.votingsystem.dto.CandidateResponse;
import com.atlas.votingsystem.entity.Candidate;
import com.atlas.votingsystem.exception.DuplicateResourceException;
import com.atlas.votingsystem.exception.ResourceNotFoundException;
import com.atlas.votingsystem.repository.CandidateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<CandidateResponse> getAllCandidates() {
        return toResponseList(candidateRepository.findAll());
    }

    public List<CandidateResponse> getCandidatesByConstituency(String constituency) {
        return toResponseList(candidateRepository.findByConstituencyIgnoreCase(constituency));
    }

    public List<CandidateResponse> getResults() {
        return toResponseList(candidateRepository.findAllByOrderByVoteCountDesc());
    }

    public List<CandidateResponse> getResultsByConstituency(String constituency) {
        return toResponseList(candidateRepository.findByConstituencyIgnoreCaseOrderByVoteCountDesc(constituency));
    }

    public CandidateResponse getCandidateById(Long id) {
        return toResponse(getCandidateEntityById(id));
    }

    public CandidateResponse createCandidate(CandidateRequest request) {
        validateUniquePartyName(request.getPartyName());
        Candidate candidate = new Candidate();
        copyRequestToCandidate(request, candidate);
        return toResponse(candidateRepository.save(candidate));
    }

    @Transactional
    public CandidateResponse updateCandidate(Long id, CandidateRequest request) {
        Candidate candidate = getCandidateEntityById(id);
        validateUniquePartyName(request.getPartyName(), id);
        copyRequestToCandidate(request, candidate);
        return toResponse(candidate);
    }

    public void deleteCandidate(Long id) {
        Candidate candidate = getCandidateEntityById(id);
        candidateRepository.delete(candidate);
    }

    @Transactional
    public CandidateResponse addVote(Long id) {
        int updatedRows = candidateRepository.incrementVoteCount(id);
        if (updatedRows == 0) {
            throw new ResourceNotFoundException("Candidate not found with id: " + id);
        }
        return getCandidateById(id);
    }

    private void copyRequestToCandidate(CandidateRequest request, Candidate candidate) {
        candidate.setName(request.getName());
        candidate.setPartyName(request.getPartyName());
        candidate.setConstituency(request.getConstituency());
        candidate.setDateOfBirth(request.getDateOfBirth());
        candidate.setManifesto(request.getManifesto());
    }

    private void validateUniquePartyName(String partyName) {
        if (candidateRepository.existsByPartyNameIgnoreCase(partyName)) {
            throw new DuplicateResourceException("Party name already exists: " + partyName);
        }
    }

    private void validateUniquePartyName(String partyName, Long candidateId) {
        if (candidateRepository.existsByPartyNameIgnoreCaseAndIdNot(partyName, candidateId)) {
            throw new DuplicateResourceException("Party name already exists: " + partyName);
        }
    }

    private Candidate getCandidateEntityById(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id: " + id));
    }

    private List<CandidateResponse> toResponseList(List<Candidate> candidates) {
        return candidates.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private CandidateResponse toResponse(Candidate candidate) {
        return new CandidateResponse(
                candidate.getId(),
                candidate.getName(),
                candidate.getPartyName(),
                candidate.getConstituency(),
                candidate.getDateOfBirth(),
                candidate.getAge(),
                candidate.getManifesto(),
                candidate.getVoteCount(),
                candidate.getCreatedAt()
        );
    }
}
