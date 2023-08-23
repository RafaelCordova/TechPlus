package com.crud.veterinaria.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
public class InformacionGeneral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String sexo;
    private String fechaNacimiento;
    @Transient
    private Integer edadCalculada; // Esto no será persistido en la base de datos
    private double peso;
    private String foto;


    public InformacionGeneral() {
    }

    public InformacionGeneral(Long id, String nombre, String sexo, String fechaNacimiento,
                              Integer edadCalculada, double peso,  String foto) {
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.edadCalculada = edadCalculada;
        this.peso = peso;
        this.foto = foto;
    }

    @PostLoad
    public void calcularEdad() {
        if (fechaNacimiento != null) {
            LocalDate ahora = LocalDate.now();
            Period periodo = Period.between(LocalDate.parse(fechaNacimiento), ahora);
            edadCalculada = periodo.getYears();
        }
    }

    public Integer getEdadCalculada() {
        return edadCalculada;
    }

    public void setEdadCalculada(Integer edadCalculada) {
        this.edadCalculada = edadCalculada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


}
