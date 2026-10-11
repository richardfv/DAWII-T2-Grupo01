package com.cibertec.reclamos.model;

public class Reclamo {

    private String numreclamo;
    private String fechareclamo;
    private String nomcliente;
    private String ultimos4tarjeta;
    private String motivo;
    private String analista = "No asignado";
    private Integer estado = 1;
    private String observacion = "por evaluar";

    public Reclamo() {
        this.analista = "No asignado";
        this.estado = 1;
        this.observacion = "por evaluar";
    }

    public Reclamo(String numreclamo, String fechareclamo, String nomcliente, String ultimos4tarjeta, String motivo) {
        this.numreclamo = numreclamo;
        this.fechareclamo = fechareclamo;
        this.nomcliente = nomcliente;
        this.ultimos4tarjeta = ultimos4tarjeta;
        this.motivo = motivo;
        this.analista = "No asignado";
        this.estado = 1;
        this.observacion = "por evaluar";
    }

    public Reclamo(String numreclamo, String fechareclamo, String nomcliente, String ultimos4tarjeta, String motivo,
                   String analista, Integer estado, String observacion) {
        this.numreclamo = numreclamo;
        this.fechareclamo = fechareclamo;
        this.nomcliente = nomcliente;
        this.ultimos4tarjeta = ultimos4tarjeta;
        this.motivo = motivo;
        this.analista = (analista != null && !analista.isBlank()) ? analista : "No asignado";
        this.estado = (estado != null) ? estado : 1;
        this.observacion = (observacion != null && !observacion.isBlank()) ? observacion : "por evaluar";
    }

    public String getNumreclamo() {
        return numreclamo;
    }

    public void setNumreclamo(String numreclamo) {
        this.numreclamo = numreclamo;
    }

    public String getFechareclamo() {
        return fechareclamo;
    }

    public void setFechareclamo(String fechareclamo) {
        this.fechareclamo = fechareclamo;
    }

    public String getNomcliente() {
        return nomcliente;
    }

    public void setNomcliente(String nomcliente) {
        this.nomcliente = nomcliente;
    }

    public String getUltimos4tarjeta() {
        return ultimos4tarjeta;
    }

    public void setUltimos4tarjeta(String ultimos4tarjeta) {
        this.ultimos4tarjeta = ultimos4tarjeta;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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
