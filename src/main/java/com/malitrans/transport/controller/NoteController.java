package com.malitrans.transport.controller;

import com.malitrans.transport.dto.NoteDTO;
import com.malitrans.transport.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @Operation(summary = "Envoyer une note")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Note envoyée")})
    @PostMapping
    public ResponseEntity<NoteDTO> send(@RequestBody NoteDTO dto) {
        return ResponseEntity.ok(service.sendNote(dto));
    }

    @Operation(summary = "Notes données")
    @ApiResponses({@ApiResponse(responseCode = "200")})
    @GetMapping("/from/{userId}")
    public List<NoteDTO> notesFrom(@PathVariable Long userId) {
        return service.notesGiven(userId);
    }

    @Operation(summary = "Notes reçues")
    @ApiResponses({@ApiResponse(responseCode = "200")})
    @GetMapping("/to/{userId}")
    public List<NoteDTO> notesTo(@PathVariable Long userId) {
        return service.notesReceived(userId);
    }
}
