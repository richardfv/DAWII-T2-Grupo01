package com.cibertec.reclamos.dto;

public class ReclamoActualizarRequest {

    private String analista;
    private Integer estado;
    private String observacion;

    public ReclamoActualizarRequest() {
    }

    public ReclamoActualizarRequest(String analista, Integer estado, String observacion) {
        this.analista = analista;
        this.estado = estado;
        this.observacion = observacion;
    }

    public String getAnalista() {
        return analista;
    }

    public void setAnalista(String analista) {
        this.analista = analista;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
