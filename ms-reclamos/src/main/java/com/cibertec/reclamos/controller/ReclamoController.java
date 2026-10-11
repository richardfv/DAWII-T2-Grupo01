package com.cibertec.reclamos.controller;

import com.cibertec.reclamos.dto.ReclamoActualizarRequest;
import com.cibertec.reclamos.model.Reclamo;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/reclamos")
public class ReclamoController {

    // Almacenamiento en memoria para reclamos
    private final Map<String, Reclamo> reclamosBD = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        // Datos iniciales de prueba
        Reclamo r1 = new Reclamo("REC-001", "2026-10-10", "Carlos Mendoza", "4589", "Consumo en tienda no realizado");
        Reclamo r2 = new Reclamo("REC-002", "2026-10-10", "Ana Torres", "7812", "Cobro duplicado por suscripcion");
        reclamosBD.put(r1.getNumreclamo(), r1);
        reclamosBD.put(r2.getNumreclamo(), r2);
    }

    // 1. Listar todos los reclamos (utilidad para pruebas)
    @GetMapping
    public ResponseEntity<Collection<Reclamo>> listarTodos() {
        return ResponseEntity.ok(reclamosBD.values());
    }

    // 2. Registrar un nuevo reclamo
    @PostMapping
    public ResponseEntity<?> registrarReclamo(@RequestBody Reclamo nuevoReclamo) {
        if (nuevoReclamo.getNumreclamo() == null || nuevoReclamo.getNumreclamo().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "El campo numreclamo es obligatorio"));
        }

        if (reclamosBD.containsKey(nuevoReclamo.getNumreclamo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Ya existe un reclamo con el numero: " + nuevoReclamo.getNumreclamo()));
        }

        // Valores por defecto si no vienen especificados
        if (nuevoReclamo.getAnalista() == null || nuevoReclamo.getAnalista().isBlank()) {
            nuevoReclamo.setAnalista("No asignado");
        }
        if (nuevoReclamo.getEstado() == null) {
            nuevoReclamo.setEstado(1);
        }
        if (nuevoReclamo.getObservacion() == null || nuevoReclamo.getObservacion().isBlank()) {
            nuevoReclamo.setObservacion("por evaluar");
        }

        reclamosBD.put(nuevoReclamo.getNumreclamo(), nuevoReclamo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoReclamo);
    }

    // 3. Consultar reclamo por numero
    @GetMapping("/{numreclamo}")
    public ResponseEntity<?> consultarPorNumero(@PathVariable String numreclamo) {
        Reclamo reclamo = reclamosBD.get(numreclamo);
        if (reclamo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Reclamo no encontrado con el numero: " + numreclamo));
        }
        return ResponseEntity.ok(reclamo);
    }

    // Actualizar reclamo
    @PutMapping("/{numreclamo}")
    public ResponseEntity<?> actualizarReclamo(@PathVariable String numreclamo,
                                               @RequestBody ReclamoActualizarRequest request) {
        Reclamo reclamo = reclamosBD.get(numreclamo);
        if (reclamo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Reclamo no encontrado con el numero: " + numreclamo));
        }

        if (request.getAnalista() != null && !request.getAnalista().isBlank()) {
            reclamo.setAnalista(request.getAnalista());
        }
        if (request.getEstado() != null) {
            reclamo.setEstado(request.getEstado());
        }
        if (request.getObservacion() != null && !request.getObservacion().isBlank()) {
            reclamo.setObservacion(request.getObservacion());
        }

        reclamosBD.put(numreclamo, reclamo);
        return ResponseEntity.ok(reclamo);
    }
}
