package com.crud.veterinaria.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class DatosInternamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String motivo;
    private String fechaIngreso;
    private String fechaSalida;
    private double pesoIngreso;
    private double temperaturaIngreso;
    private String diagnostico;
    private String comentarios;
    private double totalPagar;

    public DatosInternamiento() {
    }

    public DatosInternamiento(Long id, String motivo, String fechaIngreso, String fechaSalida, double pesoIngreso, double temperaturaIngreso, String diagnostico, String comentarios, double totalPagar) {
        this.id = id;
        this.motivo = motivo;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.pesoIngreso = pesoIngreso;
        this.temperaturaIngreso = temperaturaIngreso;
        this.diagnostico = diagnostico;
        this.comentarios = comentarios;
        this.totalPagar = totalPagar;
    }

    // ... getters, setters y constructores

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double getPesoIngreso() {
        return pesoIngreso;
    }

    public void setPesoIngreso(double pesoIngreso) {
        this.pesoIngreso = pesoIngreso;
    }

    public double getTemperaturaIngreso() {
        return temperaturaIngreso;
    }

    public void setTemperaturaIngreso(double temperaturaIngreso) {
        this.temperaturaIngreso = temperaturaIngreso;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }
}

