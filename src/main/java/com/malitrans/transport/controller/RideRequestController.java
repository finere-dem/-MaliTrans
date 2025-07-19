package com.malitrans.transport.controller;

import com.malitrans.transport.dto.RideRequestDTO;
import com.malitrans.transport.model.RideRequestStatus;
import com.malitrans.transport.service.RideRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ride")
public class RideRequestController {

    private final RideRequestService service;

    public RideRequestController(RideRequestService service) {
        this.service = service;
    }

    @Operation(summary = "Créer une demande de trajet")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Trajet créé")})
    @PostMapping
    public ResponseEntity<RideRequestDTO> create(@RequestBody RideRequestDTO dto) {
        return ResponseEntity.ok(service.createRideRequest(dto));
    }

    @Operation(summary = "Accepter une demande")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Trajet accepté"),
            @ApiResponse(responseCode = "404", description = "Non trouvé")})
    @PreAuthorize("hasRole('CHAUFFEUR')")
    @PostMapping("/{id}/accept")
    public ResponseEntity<RideRequestDTO> accept(@PathVariable Long id, @RequestParam Long chauffeurId) {
        return service.acceptRideRequest(id, chauffeurId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Modifier le statut")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Statut modifié")})
    @PatchMapping("/{id}/status")
    public ResponseEntity<RideRequestDTO> updateStatus(@PathVariable Long id, @RequestParam RideRequestStatus status) {
        return service.updateStatus(id, status)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Historique client")
    @ApiResponses({@ApiResponse(responseCode = "200")})
    @GetMapping("/client/{clientId}")
    public List<RideRequestDTO> historyClient(@PathVariable Long clientId) {
        return service.historyForClient(clientId);
    }

    @Operation(summary = "Historique chauffeur")
    @ApiResponses({@ApiResponse(responseCode = "200")})
    @GetMapping("/chauffeur/{chauffeurId}")
    public List<RideRequestDTO> historyChauffeur(@PathVariable Long chauffeurId) {
        return service.historyForChauffeur(chauffeurId);
    }
}
