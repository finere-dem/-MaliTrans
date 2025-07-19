package com.malitrans.transport.repository;

import com.malitrans.transport.model.RideRequest;
import com.malitrans.transport.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
    List<RideRequest> findByClient(Utilisateur client);
    List<RideRequest> findByChauffeur(Utilisateur chauffeur);
}
