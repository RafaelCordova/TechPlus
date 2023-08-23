package com.crud.veterinaria.controller;


import com.crud.veterinaria.model.DatosPropietario;
import com.crud.veterinaria.repository.DatosPropietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/datos-propietario")

public class DatosPropietarioController {

    @Autowired
    private DatosPropietarioRepository datosPropietarioRepository;

    @GetMapping("/editarPropietario/{id}")
    public String mostrarFormularioEditarPropietario(@PathVariable Long id, Model model) {
        DatosPropietario propietario = datosPropietarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Propietario no encontrado con ID: " + id));
        model.addAttribute("propietario", propietario);
        return "editarPropietario"; // Aquí debes retornar el nombre de tu vista para editar
    }

    @PostMapping("/actualizarPropietario/{id}")
    public String actualizarPropietario(@PathVariable Long id, @ModelAttribute DatosPropietario propietario, Model model) {
        DatosPropietario propietarioExistente = datosPropietarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Propietario no encontrado con ID: " + id));

        propietarioExistente.setTipoDocumento(propietario.getTipoDocumento());
        propietarioExistente.setNumeroDocumento(propietario.getNumeroDocumento());
        propietarioExistente.setApellidosNombres(propietario.getApellidosNombres());
        propietarioExistente.setTelefono(propietario.getTelefono());
        propietarioExistente.setCorreo(propietario.getCorreo());
        propietarioExistente.setDireccion(propietario.getDireccion());
        propietarioExistente.setReferencia(propietario.getReferencia());


        DatosPropietario propietarioActualizado = datosPropietarioRepository.save(propietarioExistente);
        model.addAttribute("propietario", propietarioActualizado);
        return "redirect:/api/datos-propietario/listarPropietarios";
    }

    @DeleteMapping("/eliminarPropietario/{id}")
    public String eliminarPropietario(@PathVariable Long id) {
        DatosPropietario propietario = datosPropietarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Propietario no encontrado con ID: " + id));
        datosPropietarioRepository.delete(propietario);

        return "redirect:/api/datos-propietario/listarPropietarios";
    }


    @GetMapping("/listarPropietarios")
    public String listarPropietarios(Model model) {
        List<DatosPropietario> propietarios = datosPropietarioRepository.findAll();
        model.addAttribute("propietarios", propietarios);
        return "listadoPropietarios";
    }

    @GetMapping("/formularioPropietario")
    public String mostrarFormulario(Model model) {
        DatosPropietario datosPropietario = new DatosPropietario();
        model.addAttribute("datosPropietario", datosPropietario);
        return "formularioPropietario";
    }

    @PostMapping("/guardarPropietario")
    public String guardarPropietario(@ModelAttribute DatosPropietario datosPropietario, Model model) {
        datosPropietarioRepository.save(datosPropietario);
        DatosPropietario nuevoDatosPropietario = new DatosPropietario();
        model.addAttribute("datosPropietario", nuevoDatosPropietario);

        return "redirect:/api/datos-propietario/listarPropietarios";
    }


    @GetMapping
    public ResponseEntity<List<DatosPropietario>> obtenerTodosLosDatosPropietario() {
        List<DatosPropietario> perfilesPropietarios = datosPropietarioRepository.findAll();
        return new ResponseEntity<>(perfilesPropietarios, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DatosPropietario> obtenerDatosPropietarioPorId(@PathVariable Long id) {
        DatosPropietario datosPropietario = datosPropietarioRepository.findById(id).orElse(null);
        if (datosPropietario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(datosPropietario);
    }

    @PostMapping
    public DatosPropietario crearDatosPropietario(@RequestBody DatosPropietario datosPropietario) {
        return datosPropietarioRepository.save(datosPropietario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosPropietario> actualizarDatosPropietario(@PathVariable Long id, @RequestBody DatosPropietario datosPropietarioActualizar) {
        DatosPropietario datosPropietario = datosPropietarioRepository.findById(id).orElse(null);
        if (datosPropietario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(datosPropietarioRepository.save(datosPropietario));
    }

 }
