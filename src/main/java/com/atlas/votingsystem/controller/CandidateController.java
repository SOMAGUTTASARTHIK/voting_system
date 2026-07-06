package com.atlas.votingsystem.controller;

import com.atlas.votingsystem.dto.CandidateRequest;
import com.atlas.votingsystem.dto.CandidateResponse;
import com.atlas.votingsystem.dto.VoteRequest;
import com.atlas.votingsystem.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public List<CandidateResponse> getCandidates(@RequestParam(required = false) String constituency) {
        if (constituency == null || constituency.isBlank()) {
            return candidateService.getAllCandidates();
        }
        return candidateService.getCandidatesByConstituency(constituency);
    }

    @GetMapping("/results")
    public List<CandidateResponse> getResults(@RequestParam(required = false) String constituency) {
        if (constituency == null || constituency.isBlank()) {
            return candidateService.getResults();
        }
        return candidateService.getResultsByConstituency(constituency);
    }

    @GetMapping("/{id}")
    public CandidateResponse getCandidate(@PathVariable Long id) {
        return candidateService.getCandidateById(id);
    }

    @PostMapping
    public ResponseEntity<CandidateResponse> createCandidate(@Valid @RequestBody CandidateRequest request) {
        CandidateResponse createdCandidate = candidateService.createCandidate(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCandidate);
    }

    @PutMapping("/{id}")
    public CandidateResponse updateCandidate(@PathVariable Long id, @Valid @RequestBody CandidateRequest request) {
        return candidateService.updateCandidate(id, request);
    }

    @PostMapping("/{id}/vote")
    public CandidateResponse voteForCandidate(@PathVariable Long id, @Valid @RequestBody VoteRequest request) {
        return candidateService.addVote(id, request.getVoterId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }
}
