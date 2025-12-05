package org.example.service;

import org.example.modelos.Persona;
import org.example.repositorios.PersonaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DirectorioTest {

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private Directorio directorio;

    @Test
    void testStorePersona() {
        Persona persona = new Persona();
        persona.setNombre("Juan");
        when(personaRepository.save(any(Persona.class))).thenReturn(persona);

        Persona saved = directorio.storePersona(persona);
        assertNotNull(saved);
        assertEquals("Juan", saved.getNombre());
    }

    @Test
    void testFindPersonaFound() {
        Persona persona = new Persona();
        persona.setIdentificacion("123");
        when(personaRepository.findByIdentificacion("123")).thenReturn(Optional.of(persona));

        Persona found = directorio.findPersonaByIdentificacion("123");
        assertEquals("123", found.getIdentificacion());
    }

    @Test
    void testDeletePersona() {
        Persona persona = new Persona();
        persona.setIdentificacion("123");
        when(personaRepository.findByIdentificacion("123")).thenReturn(Optional.of(persona));

        directorio.deletePersonaByIdentificacion("123");
        verify(personaRepository, times(1)).delete(persona);
    }
}
