package org.example.repositorios;

import org.example.modelos.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByIdentificacion(String identificacion);
    void deleteByIdentificacion(String identificacion);
}
