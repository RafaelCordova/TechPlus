package com.crud.veterinaria.controller;

import com.crud.veterinaria.model.DatosInternamiento;
import com.crud.veterinaria.repository.DatosInternamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/datos-internamiento")
public class DatosInternamientoController {


    @Autowired
    private DatosInternamientoRepository datosInternamientoRepository;

    @GetMapping
    public List<DatosInternamiento> obtenerTodosLosDatosInternamiento() {
        return datosInternamientoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosInternamiento> obtenerDatosInternamientoPorId(@PathVariable Long id) {
        DatosInternamiento datosInternamiento = datosInternamientoRepository.findById(id).orElse(null);
        if (datosInternamiento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(datosInternamiento);
    }

    @PostMapping
    public DatosInternamiento crearDatosInternamiento(@RequestBody DatosInternamiento datosInternamiento) {
        return datosInternamientoRepository.save(datosInternamiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosInternamiento> actualizarDatosInternamiento(@PathVariable Long id, @RequestBody DatosInternamiento datosInternamientoActualizar) {
        DatosInternamiento datosInternamiento = datosInternamientoRepository.findById(id).orElse(null);
        if (datosInternamiento == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar las propiedades de los datos de internamiento con la información actualizada

        return ResponseEntity.ok(datosInternamientoRepository.save(datosInternamiento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDatosInternamiento(@PathVariable Long id) {
        DatosInternamiento datosInternamiento = datosInternamientoRepository.findById(id).orElse(null);
        if (datosInternamiento == null) {
            return ResponseEntity.notFound().build();
        }

        // Eliminar los datos de internamiento

        return ResponseEntity.noContent().build();
    }
}