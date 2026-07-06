package com.atlas.votingsystem.controller;

import com.atlas.votingsystem.dto.VoterRequest;
import com.atlas.votingsystem.dto.VoterResponse;
import com.atlas.votingsystem.service.VoterService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/voters")
public class VoterController {

    private final VoterService voterService;

    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @GetMapping
    public List<VoterResponse> getVoters() {
        return voterService.getAllVoters();
    }

    @GetMapping("/{id}")
    public VoterResponse getVoter(@PathVariable Long id) {
        return voterService.getVoterById(id);
    }

    @PostMapping
    public ResponseEntity<VoterResponse> createVoter(@Valid @RequestBody VoterRequest request) {
        VoterResponse createdVoter = voterService.createVoter(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVoter);
    }

    @PutMapping("/{id}")
    public VoterResponse updateVoter(@PathVariable Long id, @Valid @RequestBody VoterRequest request) {
        return voterService.updateVoter(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoter(@PathVariable Long id) {
        voterService.deleteVoter(id);
        return ResponseEntity.noContent().build();
    }
}
