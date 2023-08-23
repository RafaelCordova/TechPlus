package com.crud.veterinaria.controller;

import com.crud.veterinaria.model.Raza;
import com.crud.veterinaria.model.Tamano;
import com.crud.veterinaria.repository.RazaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/datos-raza")
public class RazaController {

    @Autowired
    private RazaRepository razaRepository;

    @GetMapping("/listarRazas")
    public String listarRazas(Model model) {
        List<Raza> razas = razaRepository.findAll();
        model.addAttribute("razas", razas);
        return "listadoRazas";
    }

    @GetMapping("/editarRaza/{id}")
    public String mostrarFormularioEditarRaza(@PathVariable Long id, Model model) {
        Raza raza = razaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Raza no encontrada con ID: " + id));
        model.addAttribute("raza", raza);
        return "editarRaza";
    }

    @PostMapping("/actualizarRaza/{id}")
    public String actualizarRaza(@PathVariable Long id, @ModelAttribute Raza raza, Model model) {
        Raza razaExistente = razaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Raza no encontrada con ID: " + id));
        razaExistente.setNombre(raza.getNombre());
        Raza razaActualizada = razaRepository.save(razaExistente);
        model.addAttribute("raza", razaActualizada);
        return "redirect:/api/datos-raza/listarRazas";
    }


    @DeleteMapping("/eliminarRaza/{id}")
    public String eliminarRaza(@PathVariable Long id) {
        Raza raza = razaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Raza no encontrada con ID: " + id));
        razaRepository.delete(raza);

        return "redirect:/api/datos-raza/listarRazas";
    }


    @GetMapping("/formularioRaza")
    public String mostrarFormularioRaza(Model model) {
        Raza raza = new Raza();
        model.addAttribute("raza",raza);
      //  model.addAttribute("raza", raza);
        return "formularioRaza";
    }

    @PostMapping("/guardarRaza")
    public String guardarRaza(@ModelAttribute Raza raza, Model model) {
        razaRepository.save(raza);
        Raza nuevaRaza = new Raza();
        model.addAttribute("raza", nuevaRaza);

        return "redirect:/api/datos-raza/listarRazas";
    }

    @GetMapping("/{razaId}")
    public ResponseEntity<Raza> obtenerRaza(@PathVariable Long razaId) {
        Raza raza = razaRepository.findById(razaId)
                .orElseThrow(() -> new ResourceNotFoundException("Raza no encontrada"));
        return ResponseEntity.ok(raza);
    }

    @GetMapping
    public ResponseEntity<List<Raza>> listarRazas() {
        List<Raza> razas = razaRepository.findAll();
        return ResponseEntity.ok(razas);
    }

    @PostMapping
    public ResponseEntity<Raza> crearRaza(@RequestBody Raza raza) {
        Raza nuevaRaza = razaRepository.save(raza);
        return new ResponseEntity<>(nuevaRaza, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Raza> actualizarRaza(@PathVariable Long id, @RequestBody Raza razaActualizada) {
        Raza razaExistente = razaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Raza no encontrado con id: " + id));

        razaExistente.setNombre(razaActualizada.getNombre());
        // Actualiza otros atributos según corresponda

        Raza razaActualizadoDb = razaRepository.save(razaExistente);
        return new ResponseEntity<>(razaActualizadoDb, HttpStatus.OK);
    }

  }
