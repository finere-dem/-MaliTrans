package com.malitrans.transport.dto;

import com.malitrans.transport.model.RideRequestStatus;
import java.time.LocalDateTime;

public class RideRequestDTO {
    private Long id;
    private String origin;
    private String destination;
    private Long clientId;
    private Long chauffeurId;
    private RideRequestStatus status;
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }

    public Long getChauffeurId() { return chauffeurId; }
    public void setChauffeurId(Long chauffeurId) { this.chauffeurId = chauffeurId; }

    public RideRequestStatus getStatus() { return status; }
    public void setStatus(RideRequestStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
