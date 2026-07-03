package com.atlas.votingsystem.controller;

import com.atlas.votingsystem.dto.CandidateRequest;
import com.atlas.votingsystem.entity.Candidate;
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
    public List<Candidate> getCandidates(@RequestParam(required = false) String constituency) {
        if (constituency == null || constituency.isBlank()) {
            return candidateService.getAllCandidates();
        }
        return candidateService.getCandidatesByConstituency(constituency);
    }

    @GetMapping("/{id}")
    public Candidate getCandidate(@PathVariable Long id) {
        return candidateService.getCandidateById(id);
    }

    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@Valid @RequestBody CandidateRequest request) {
        Candidate createdCandidate = candidateService.createCandidate(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCandidate);
    }

    @PutMapping("/{id}")
    public Candidate updateCandidate(@PathVariable Long id, @Valid @RequestBody CandidateRequest request) {
        return candidateService.updateCandidate(id, request);
    }

    @PostMapping("/{id}/vote")
    public Candidate voteForCandidate(@PathVariable Long id) {
        return candidateService.addVote(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }
}
