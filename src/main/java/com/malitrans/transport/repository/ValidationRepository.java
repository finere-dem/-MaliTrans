package com.malitrans.transport.repository;

import com.malitrans.transport.model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValidationRepository extends JpaRepository<Validation, Long> {
    Optional<Validation> findByToken(String token);
}
