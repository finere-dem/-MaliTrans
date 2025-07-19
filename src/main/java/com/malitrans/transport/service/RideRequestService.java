package com.malitrans.transport.service;

import com.malitrans.transport.dto.RideRequestDTO;
import com.malitrans.transport.mapper.RideRequestMapper;
import com.malitrans.transport.model.RideRequest;
import com.malitrans.transport.model.RideRequestStatus;
import com.malitrans.transport.model.Utilisateur;
import com.malitrans.transport.repository.RideRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RideRequestService {

    private final RideRequestRepository repository;
    private final UtilisateurService utilisateurService;
    private final RideRequestMapper mapper;

    public RideRequestService(RideRequestRepository repository, UtilisateurService utilisateurService, RideRequestMapper mapper) {
        this.repository = repository;
        this.utilisateurService = utilisateurService;
        this.mapper = mapper;
    }

    public RideRequestDTO createRideRequest(RideRequestDTO dto) {
        RideRequest entity = mapper.toEntity(dto);
        utilisateurService.findById(dto.getClientId()).ifPresent(entity::setClient);
        RideRequest saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public Optional<RideRequestDTO> acceptRideRequest(Long id, Long chauffeurId) {
        Optional<RideRequest> optional = repository.findById(id);
        Optional<Utilisateur> chauffeur = utilisateurService.findById(chauffeurId);
        if (optional.isPresent() && chauffeur.isPresent()) {
            RideRequest rr = optional.get();
            rr.setChauffeur(chauffeur.get());
            rr.setStatus(RideRequestStatus.ACCEPTED);
            return Optional.of(mapper.toDto(repository.save(rr)));
        }
        return Optional.empty();
    }

    public Optional<RideRequestDTO> updateStatus(Long id, RideRequestStatus status) {
        return repository.findById(id).map(rr -> {
            rr.setStatus(status);
            return mapper.toDto(repository.save(rr));
        });
    }

    public List<RideRequestDTO> historyForClient(Long clientId) {
        return utilisateurService.findById(clientId)
                .map(repository::findByClient)
                .orElse(List.of())
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public List<RideRequestDTO> historyForChauffeur(Long chauffeurId) {
        return utilisateurService.findById(chauffeurId)
                .map(repository::findByChauffeur)
                .orElse(List.of())
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
