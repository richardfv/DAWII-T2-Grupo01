package com.cibertec.resolucion.client;

import com.cibertec.resolucion.dto.ReclamoActualizarDto;
import com.cibertec.resolucion.dto.ReclamoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-reclamos")
public interface ReclamoClient {

    @GetMapping("/reclamos/{numreclamo}")
    ReclamoDto consultarReclamo(@PathVariable("numreclamo") String numreclamo);

    @PutMapping("/reclamos/{numreclamo}")
    ReclamoDto actualizarReclamo(@PathVariable("numreclamo") String numreclamo,
                                 @RequestBody ReclamoActualizarDto request);
}
