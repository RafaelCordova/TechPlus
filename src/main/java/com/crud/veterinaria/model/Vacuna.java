package com.crud.veterinaria.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Vacuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String tipo;
    private String comentarios;

    @ManyToOne
    @JoinColumn(name = "perfil_mascota_id") // Asegúrate de usar el nombre correcto de la columna
    private PerfilMascota perfilMascota;

    public PerfilMascota getPerfilMascota() {
        return perfilMascota;
    }

    public void setPerfilMascota(PerfilMascota perfilMascota) {
        this.perfilMascota = perfilMascota;
    }

    public Vacuna() {
    }

    public Vacuna(Long id, LocalDate fecha, String tipo, String comentarios) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.comentarios = comentarios;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

}