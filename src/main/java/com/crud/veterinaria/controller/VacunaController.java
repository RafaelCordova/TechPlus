package com.crud.veterinaria.controller;

import com.crud.veterinaria.model.Vacuna;
import com.crud.veterinaria.model.PerfilMascota;
import com.crud.veterinaria.repository.PerfilMascotaRepository;
import com.crud.veterinaria.repository.VacunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/vacunas")
public class VacunaController {

    private final VacunaRepository vacunaRepository;
    private final PerfilMascotaRepository perfilMascotaRepository;

    @Autowired
    public VacunaController(VacunaRepository vacunaRepository, PerfilMascotaRepository perfilMascotaRepository) {
        this.vacunaRepository = vacunaRepository;
        this.perfilMascotaRepository = perfilMascotaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Vacuna>> obtenerTodasLasVacunas() {
        List<Vacuna> vacunas = (List<Vacuna>) vacunaRepository.findAll();
        return new ResponseEntity<>(vacunas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVacunaPorId(@PathVariable Long id) {
        Optional<Vacuna> optionalVacuna = vacunaRepository.findById(id);
        if (optionalVacuna.isPresent()) {
            return new ResponseEntity<>(optionalVacuna.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Vacuna no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/perfil-mascota/{perfilMascotaId}/agregar-vacuna")
    public ResponseEntity<?> agregarVacuna(@PathVariable Long perfilMascotaId, @RequestBody Vacuna vacuna) {
        try {
            Optional<PerfilMascota> optionalPerfilMascota = perfilMascotaRepository.findById(perfilMascotaId);

            if (optionalPerfilMascota.isPresent()) {
                PerfilMascota perfilMascota = optionalPerfilMascota.get();
                perfilMascota.getHistorialVacunas().add(vacuna);
                PerfilMascota perfilMascotaGuardado = perfilMascotaRepository.save(perfilMascota);

                return new ResponseEntity<>(perfilMascotaGuardado, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Perfil de mascota no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarVacuna(@PathVariable Long id, @RequestBody Vacuna vacunaActualizada) {
        try {
            Optional<Vacuna> optionalVacuna = vacunaRepository.findById(id);
            if (optionalVacuna.isPresent()) {
                Vacuna vacunaExistente = optionalVacuna.get();
                vacunaExistente.setFecha(vacunaActualizada.getFecha());
                vacunaExistente.setTipo(vacunaActualizada.getTipo());
                vacunaExistente.setComentarios(vacunaActualizada.getComentarios());

                Vacuna vacunaActualizadaGuardada = vacunaRepository.save(vacunaExistente);
                return new ResponseEntity<>(vacunaActualizadaGuardada, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Vacuna no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarVacuna(@PathVariable Long id) {
        try {
            Optional<Vacuna> optionalVacuna = vacunaRepository.findById(id);
            if (optionalVacuna.isPresent()) {
                vacunaRepository.delete(optionalVacuna.get());
                return new ResponseEntity<>("Vacuna eliminada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Vacuna no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}