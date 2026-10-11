package com.cibertec.resolucion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResolucionResponseDto {

    private String codigo;
    private String mensaje;
    private String numreclamo;
    private String nomcliente;
    private String ultimos4tarjeta;
    private Integer estado;
    @JsonProperty("descripcion_estado")
    private String descripcionEstado;
    private String analista;
    private String observacion;

    public ResolucionResponseDto() {
    }

    public ResolucionResponseDto(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNumreclamo() {
        return numreclamo;
    }

    public void setNumreclamo(String numreclamo) {
        this.numreclamo = numreclamo;
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    public String getAnalista() {
        return analista;
    }

    public void setAnalista(String analista) {
        this.analista = analista;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
