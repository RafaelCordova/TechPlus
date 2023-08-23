package com.crud.veterinaria.controller;

import com.crud.veterinaria.model.Tamano;
import com.crud.veterinaria.repository.TamanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/datos-tamano")
public class TamanoController {

    private final TamanoRepository tamanoRepository;

    @Autowired
    public TamanoController(TamanoRepository tamanoRepository) {
        this.tamanoRepository = tamanoRepository;
    }


    @GetMapping("/listarTamanos")
    public String listarTamanos(Model model) {
        List<Tamano> tamanos = tamanoRepository.findAll();
        model.addAttribute("tamanos", tamanos);
        return "listadoTamanos";
    }

    @GetMapping("/editarTamano/{id}")
    public String mostrarFormularioEditarTamano(@PathVariable Long id, Model model) {
        Tamano tamano = tamanoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tamaño no encontrado con ID: " + id));
        model.addAttribute("tamano", tamano);
        return "editarTamano";
    }

    @PostMapping("/actualizarTamano/{id}")
    public String actualizarTamano(@PathVariable Long id, @ModelAttribute Tamano tamano, Model model) {
        Tamano tamanoExistente = tamanoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tamaño no encontrado con ID: " + id));
        tamanoExistente.setNombre(tamano.getNombre());
        Tamano tamanoActualizado = tamanoRepository.save(tamanoExistente);
        model.addAttribute("tamano", tamanoActualizado);
        return "redirect:/api/datos-tamano/listarTamanos";
    }

    @DeleteMapping("/eliminarTamano/{id}")
    public String eliminarTamano(@PathVariable Long id) {
        Tamano tamano = tamanoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tamaño no encontrado con ID: " + id));
        tamanoRepository.delete(tamano);
        return "redirect:/api/datos-tamano/listarTamanos";
    }

    @GetMapping("/formularioTamano")
    public String mostrarFormularioTamano(Model model) {
        Tamano tamano = new Tamano();
        model.addAttribute("tamano", tamano);
        return "formularioTamano";
    }

    @PostMapping("/guardarTamano")
    public String guardarTamano(@ModelAttribute Tamano tamano, Model model) {
        tamanoRepository.save(tamano);
        return "redirect:/api/datos-tamano/listarTamanos";
    }



    @GetMapping
    public ResponseEntity<List<Tamano>> obtenerTodosLosTamanos() {
        List<Tamano> tamanos = tamanoRepository.findAll();
        return new ResponseEntity<>(tamanos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tamano> obtenerTamanoPorId(@PathVariable Long id) {
        Tamano tamano = tamanoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tamano no encontrado con id: " + id));
        return new ResponseEntity<>(tamano, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Tamano> actualizarTamano(@PathVariable Long id, @RequestBody Tamano tamanoActualizado) {
        Tamano tamanoExistente = tamanoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tamano no encontrado con id: " + id));

        tamanoExistente.setNombre(tamanoActualizado.getNombre());
        // Actualiza otros atributos según corresponda

        Tamano tamanoActualizadoDb = tamanoRepository.save(tamanoExistente);
        return new ResponseEntity<>(tamanoActualizadoDb, HttpStatus.OK);
    }


}
