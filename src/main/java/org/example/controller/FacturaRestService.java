package org.example.controller;

import org.example.modelos.Factura;
import org.example.service.Ventas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaRestService {

    @Autowired
    private Ventas ventas;

    @PostMapping("/{identificacionPersona}")
    public ResponseEntity<Factura> crearFactura(@PathVariable String identificacionPersona,
                                                @RequestBody Factura factura) {
        Factura nuevaFactura = ventas.storeFactura(identificacionPersona, factura);
        return new ResponseEntity<>(nuevaFactura, HttpStatus.CREATED);
    }

    @GetMapping("/{identificacionPersona}")
    public ResponseEntity<List<Factura>> obtenerFacturasPorPersona(@PathVariable String identificacionPersona) {
        List<Factura> facturas = ventas.findFacturasByPersona(identificacionPersona);
        return ResponseEntity.ok(facturas);
    }
}
