package com.malitrans.transport.service;

import com.malitrans.transport.dto.NoteDTO;
import com.malitrans.transport.mapper.NoteMapper;
import com.malitrans.transport.model.Note;
import com.malitrans.transport.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository repository;
    private final UtilisateurService utilisateurService;
    private final NoteMapper mapper;

    public NoteService(NoteRepository repository, UtilisateurService utilisateurService, NoteMapper mapper) {
        this.repository = repository;
        this.utilisateurService = utilisateurService;
        this.mapper = mapper;
    }

    public NoteDTO sendNote(NoteDTO dto) {
        Note note = mapper.toEntity(dto);
        utilisateurService.findById(dto.getFromUserId()).ifPresent(note::setFromUser);
        utilisateurService.findById(dto.getToUserId()).ifPresent(note::setToUser);
        return mapper.toDto(repository.save(note));
    }

    public List<NoteDTO> notesGiven(Long userId) {
        return utilisateurService.findById(userId)
                .map(repository::findByFromUser)
                .orElse(List.of())
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public List<NoteDTO> notesReceived(Long userId) {
        return utilisateurService.findById(userId)
                .map(repository::findByToUser)
                .orElse(List.of())
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
