package com.cibertec.resolucion.controller;

import com.cibertec.resolucion.client.ReclamoClient;
import com.cibertec.resolucion.dto.EvaluarRequestDto;
import com.cibertec.resolucion.dto.ReclamoActualizarDto;
import com.cibertec.resolucion.dto.ReclamoDto;
import com.cibertec.resolucion.dto.ResolucionResponseDto;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resolucion")
public class ResolucionController {

    private final ReclamoClient reclamoClient;

    public ResolucionController(ReclamoClient reclamoClient) {
        this.reclamoClient = reclamoClient;
    }

    @PostMapping("/evaluar")
    public ResponseEntity<ResolucionResponseDto> evaluarReclamo(@RequestBody EvaluarRequestDto request) {

        // Validar numreclamo
        if (request.getNumreclamo() == null || request.getNumreclamo().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResolucionResponseDto("400", "Error: El campo numreclamo es obligatorio"));
        }

        // Validar estado (2: Procedente, 3: Improcedente)
        if (request.getEstado() == null || (request.getEstado() != 2 && request.getEstado() != 3)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResolucionResponseDto("400",
                            "Error: El estado debe ser 2 (Procedente) o 3 (Improcedente)"));
        }

        // Consultar existencia del reclamo en ms-reclamos
        try {
            reclamoClient.consultarReclamo(request.getNumreclamo());
        } catch (FeignException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResolucionResponseDto("404",
                            "Error: El reclamo " + request.getNumreclamo() + " no existe en el sistema"));
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResolucionResponseDto("503",
                            "Error: No se pudo conectar con ms-reclamos"));
        }

        // Actualizar el reclamo
        ReclamoActualizarDto actualizacion = new ReclamoActualizarDto(
                request.getAnalista(),
                request.getEstado(),
                request.getObservacion());
        ReclamoDto reclamoActualizado = reclamoClient.actualizarReclamo(request.getNumreclamo(), actualizacion);

        // Retornar respuesta
        ResolucionResponseDto respuesta = new ResolucionResponseDto();
        respuesta.setCodigo("200");
        respuesta.setMensaje("Resolucion del reclamo completada exitosamente");
        respuesta.setNumreclamo(reclamoActualizado.getNumreclamo());
        respuesta.setNomcliente(reclamoActualizado.getNomcliente());
        respuesta.setUltimos4tarjeta(reclamoActualizado.getUltimos4tarjeta());
        respuesta.setEstado(reclamoActualizado.getEstado());
        respuesta.setDescripcionEstado(describirEstado(reclamoActualizado.getEstado()));
        respuesta.setAnalista(reclamoActualizado.getAnalista());
        respuesta.setObservacion(reclamoActualizado.getObservacion());

        return ResponseEntity.ok(respuesta);
    }

    private String describirEstado(Integer estado) {
        if (estado == null) {
            return null;
        }
        return switch (estado) {
            case 1 -> "Pendiente";
            case 2 -> "Procedente";
            case 3 -> "Improcedente";
            default -> "Desconocido";
        };
    }
}
