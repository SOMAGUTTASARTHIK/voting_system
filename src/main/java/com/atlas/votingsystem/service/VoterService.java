package com.atlas.votingsystem.service;

import com.atlas.votingsystem.dto.VoterRequest;
import com.atlas.votingsystem.dto.VoterResponse;
import com.atlas.votingsystem.entity.Voter;
import com.atlas.votingsystem.exception.BadRequestException;
import com.atlas.votingsystem.exception.DuplicateResourceException;
import com.atlas.votingsystem.exception.ResourceNotFoundException;
import com.atlas.votingsystem.repository.VoterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoterService {

    private final VoterRepository voterRepository;

    public VoterService(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    public List<VoterResponse> getAllVoters() {
        return toResponseList(voterRepository.findAll());
    }

    public VoterResponse getVoterById(Long id) {
        return toResponse(getVoterEntityById(id));
    }

    public VoterResponse createVoter(VoterRequest request) {
        normalizeRequest(request);
        validateVotingAge(request);
        validateUniqueVoterDetails(request);

        Voter voter = new Voter();
        copyRequestToVoter(request, voter);
        return toResponse(voterRepository.save(voter));
    }

    @Transactional
    public VoterResponse updateVoter(Long id, VoterRequest request) {
        normalizeRequest(request);
        validateVotingAge(request);
        Voter voter = getVoterEntityById(id);
        validateUniqueVoterDetails(request, id);
        copyRequestToVoter(request, voter);
        return toResponse(voter);
    }

    public void deleteVoter(Long id) {
        Voter voter = getVoterEntityById(id);
        voterRepository.delete(voter);
    }

    private void copyRequestToVoter(VoterRequest request, Voter voter) {
        voter.setName(request.getName());
        voter.setDateOfBirth(request.getDateOfBirth());
        voter.setPhoneNumber(request.getPhoneNumber());
        voter.setAadharNo(request.getAadhaarNo());
        voter.setPanNumber(request.getPanNumber());
        voter.setRole(request.getRole());
    }

    private void normalizeRequest(VoterRequest request) {
        if (request.getPanNumber() != null) {
            request.setPanNumber(request.getPanNumber().toUpperCase());
        }
    }

    private void validateVotingAge(VoterRequest request) {
        if (request.getDateOfBirth() == null) {
            return;
        }

        int age = Period.between(request.getDateOfBirth(), LocalDate.now()).getYears();
        if (age < 18) {
            throw new BadRequestException("Voter below 18 years is not authorized for voting system");
        }
    }

    private void validateUniqueVoterDetails(VoterRequest request) {
        if (voterRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists: " + request.getPhoneNumber());
        }
        if (voterRepository.existsByAadharNo(request.getAadhaarNo())) {
            throw new DuplicateResourceException("Aadhar number already exists: " + request.getAadhaarNo());
        }
        if (voterRepository.existsByPanNumberIgnoreCase(request.getPanNumber())) {
            throw new DuplicateResourceException("PAN number already exists: " + request.getPanNumber());
        }
    }

    private void validateUniqueVoterDetails(VoterRequest request, Long voterId) {
        if (voterRepository.existsByPhoneNumberAndIdNot(request.getPhoneNumber(), voterId)) {
            throw new DuplicateResourceException("Phone number already exists: " + request.getPhoneNumber());
        }
        if (voterRepository.existsByAadharNoAndIdNot(request.getAadhaarNo(), voterId)) {
            throw new DuplicateResourceException("Aadhar number already exists: " + request.getAadhaarNo());
        }
        if (voterRepository.existsByPanNumberIgnoreCaseAndIdNot(request.getPanNumber(), voterId)) {
            throw new DuplicateResourceException("PAN number already exists: " + request.getPanNumber());
        }
    }

    private Voter getVoterEntityById(Long id) {
        return voterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voter not found with id: " + id));
    }

    private List<VoterResponse> toResponseList(List<Voter> voters) {
        return voters.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private VoterResponse toResponse(Voter voter) {
        return new VoterResponse(
                voter.getId(),
                voter.getName(),
                voter.getDateOfBirth(),
                voter.getPhoneNumber(),
                voter.getAadharNo(),
                voter.getPanNumber(),
                voter.getAge(),
                voter.getRole(),
                voter.getCreatedAt(),
                voter.isHasVoted(),
                voter.getVotedCandidateId(),
                voter.getVotedAt()
        );
    }
}
