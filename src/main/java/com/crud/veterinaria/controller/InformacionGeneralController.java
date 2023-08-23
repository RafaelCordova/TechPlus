package com.crud.veterinaria.controller;


import com.crud.veterinaria.model.InformacionGeneral;
import com.crud.veterinaria.repository.InformacionGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/informacion-general")
public class InformacionGeneralController {

    @Autowired
    private InformacionGeneralRepository informacionGeneralRepository;

    @GetMapping
    public List<InformacionGeneral> obtenerTodaLaInformacionGeneral() {
        return informacionGeneralRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InformacionGeneral> obtenerInformacionGeneralPorId(@PathVariable Long id) {
        InformacionGeneral informacionGeneral = informacionGeneralRepository.findById(id).orElse(null);
        if (informacionGeneral == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(informacionGeneral);
    }

    @PostMapping
    public InformacionGeneral crearInformacionGeneral(@RequestBody InformacionGeneral informacionGeneral) {
        return informacionGeneralRepository.save(informacionGeneral);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InformacionGeneral> actualizarInformacionGeneral(@PathVariable Long id, @RequestBody InformacionGeneral informacionGeneralActualizar) {
        InformacionGeneral informacionGeneral = informacionGeneralRepository.findById(id).orElse(null);
        if (informacionGeneral == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar las propiedades de la información general con la información actualizada

        return ResponseEntity.ok(informacionGeneralRepository.save(informacionGeneral));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInformacionGeneral(@PathVariable Long id) {
        InformacionGeneral informacionGeneral = informacionGeneralRepository.findById(id).orElse(null);
        if (informacionGeneral == null) {
            return ResponseEntity.notFound().build();
        }

        // Eliminar la información general

        return ResponseEntity.noContent().build();
    }
}
