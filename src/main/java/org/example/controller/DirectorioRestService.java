package org.example.controller;

import jakarta.validation.Valid;
import org.example.modelos.Persona;
import org.example.service.Directorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/directorio")
public class DirectorioRestService {

    @Autowired
    private Directorio directorio;

    @PostMapping
    public ResponseEntity<Persona> createPersona(@Valid @RequestBody Persona persona) {
        return new ResponseEntity<>(directorio.storePersona(persona), HttpStatus.CREATED);
    }

    @GetMapping("/{identificacion}")
    public ResponseEntity<Persona> getPersona(@PathVariable String identificacion) {
        return ResponseEntity.ok(directorio.findPersonaByIdentificacion(identificacion));
    }

    @GetMapping
    public ResponseEntity<Page<Persona>> getAllPersonas(Pageable pageable) {
        return ResponseEntity.ok(directorio.findPersonas(pageable));
    }

    @DeleteMapping("/{identificacion}")
    public ResponseEntity<Void> deletePersona(@PathVariable String identificacion) {
        directorio.deletePersonaByIdentificacion(identificacion);
        return ResponseEntity.noContent().build();
    }
}
