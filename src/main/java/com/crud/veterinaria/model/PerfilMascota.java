package com.crud.veterinaria.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class PerfilMascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "informacion_general_id")
    private InformacionGeneral informacionGeneral;

    @ManyToOne
    @JoinColumn(name = "raza_id")
    private Raza raza;

    @ManyToOne
    @JoinColumn(name = "tamano_id")
    private Tamano tamano;

    @JoinColumn(name = "perfil_mascota_id")
    @ElementCollection
    private List<String> alergias = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "datos_propietario_id")
    private DatosPropietario datosPropietario;

    @OneToOne()
    @JoinColumn(name = "datos_internamiento_id")
    private DatosInternamiento datosInternamiento;

    @OneToMany( orphanRemoval = true)
    private List<Vacuna> historialVacunas = new ArrayList<>();
    // ... getters, setters y constructores

    public PerfilMascota() {
    }

    public PerfilMascota(Long id, InformacionGeneral informacionGeneral, Raza raza, Tamano tamano, List<String> alergias, DatosPropietario datosPropietario, DatosInternamiento datosInternamiento, List<Vacuna> historialVacunas) {
        this.id = id;
        this.informacionGeneral = informacionGeneral;
        this.raza = raza;
        this.tamano = tamano;
        this.alergias = alergias;
        this.datosPropietario = datosPropietario;
        this.datosInternamiento = datosInternamiento;
        this.historialVacunas = historialVacunas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InformacionGeneral getInformacionGeneral() {
        return informacionGeneral;
    }

    public void setInformacionGeneral(InformacionGeneral informacionGeneral) {
        this.informacionGeneral = informacionGeneral;
    }

    public List<String> getAlergias() {
        return alergias;
    }

    public void setAlergias(List<String> alergias) {
        this.alergias = alergias;
    }

    public DatosPropietario getDatosPropietario() {
        return datosPropietario;
    }

    public void setDatosPropietario(DatosPropietario datosPropietario) {
        this.datosPropietario = datosPropietario;
    }

    public DatosInternamiento getDatosInternamiento() {
        return datosInternamiento;
    }

    public void setDatosInternamiento(DatosInternamiento datosInternamiento) {
        this.datosInternamiento = datosInternamiento;
    }

    public List<Vacuna> getHistorialVacunas() {
        return historialVacunas;
    }

    public void setHistorialVacunas(List<Vacuna> historialVacunas) {
        this.historialVacunas = historialVacunas;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public Tamano getTamano() {
        return tamano;
    }

    public void setTamano(Tamano tamano) {
        this.tamano = tamano;
    }
}

