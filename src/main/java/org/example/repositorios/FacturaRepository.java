package org.example.repositorios;

import org.example.modelos.Factura;
import org.example.modelos.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByPersona(Persona persona);
}
