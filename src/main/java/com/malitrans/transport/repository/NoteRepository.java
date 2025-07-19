package com.malitrans.transport.repository;

import com.malitrans.transport.model.Note;
import com.malitrans.transport.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByFromUser(Utilisateur user);
    List<Note> findByToUser(Utilisateur user);
}
