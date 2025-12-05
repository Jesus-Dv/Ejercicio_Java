package org.example.service;

import jakarta.transaction.Transactional;
import org.example.exception.ResourceNotFoundException;
import org.example.modelos.Persona;
import org.example.repositorios.PersonaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class Directorio {

    private static final Logger logger = LoggerFactory.getLogger(Directorio.class);

    @Autowired
    private PersonaRepository personaRepository;

    public Persona storePersona(Persona persona) {
        logger.info("Guardando persona: {}", persona.getNombre());
        return personaRepository.save(persona);
    }

    public Persona findPersonaByIdentificacion(String identificacion) {
        return personaRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con ID: " + identificacion));
    }

    public Page<Persona> findPersonas(Pageable pageable) {
        return personaRepository.findAll(pageable);
    }

    @Transactional
    public void deletePersonaByIdentificacion(String identificacion) {
        logger.warn("Eliminando persona con identificacion: {}", identificacion);
        Persona persona = findPersonaByIdentificacion(identificacion);
        personaRepository.delete(persona); // Cascada elimina facturas automáticamente
    }
}
