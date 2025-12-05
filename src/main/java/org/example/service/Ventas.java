package org.example.service;

import org.example.modelos.Factura;
import org.example.modelos.Persona;
import org.example.repositorios.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Ventas {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private Directorio directorio;

    public Factura storeFactura(String identificacionPersona, Factura factura) {
        Persona persona = directorio.findPersonaByIdentificacion(identificacionPersona);
        factura.setPersona(persona);
        return facturaRepository.save(factura);
    }

    public List<Factura> findFacturasByPersona(String identificacion) {
        Persona persona = directorio.findPersonaByIdentificacion(identificacion);
        return facturaRepository.findByPersona(persona);
    }
}
