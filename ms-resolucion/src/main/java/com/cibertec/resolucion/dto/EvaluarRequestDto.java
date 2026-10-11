package com.cibertec.resolucion.dto;

public class EvaluarRequestDto {

    private String numreclamo;
    private String analista;
    private Integer estado;
    private String observacion;

    public EvaluarRequestDto() {
    }

    public String getNumreclamo() {
        return numreclamo;
    }

    public void setNumreclamo(String numreclamo) {
        this.numreclamo = numreclamo;
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
