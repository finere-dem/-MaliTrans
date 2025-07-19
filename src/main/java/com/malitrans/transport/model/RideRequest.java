package com.malitrans.transport.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RideRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origin;
    private String destination;

    @ManyToOne
    private Utilisateur client;

    @ManyToOne
    private Utilisateur chauffeur;

    @Enumerated(EnumType.STRING)
    private RideRequestStatus status = RideRequestStatus.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public Utilisateur getClient() { return client; }
    public void setClient(Utilisateur client) { this.client = client; }

    public Utilisateur getChauffeur() { return chauffeur; }
    public void setChauffeur(Utilisateur chauffeur) { this.chauffeur = chauffeur; }

    public RideRequestStatus getStatus() { return status; }
    public void setStatus(RideRequestStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
