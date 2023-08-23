package com.crud.veterinaria.controller;



import com.crud.veterinaria.model.*;
import com.crud.veterinaria.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

//@RestController

@Controller
@RequestMapping("/api/mascotas")
public class PerfilMascotaController {

    @Autowired
    private PerfilMascotaRepository perfilMascotaRepository;

    @Autowired
    private RazaRepository razaRepository;

    @Autowired
    private TamanoRepository tamanoRepository;

    @Autowired
    private DatosPropietarioRepository datosPropietarioRepository;

    @Autowired
    private DatosInternamientoRepository datosInternamientoRepository;

    @Autowired
    private InformacionGeneralRepository informacionGeneralRepository;


    @GetMapping("/listarMascotas")
    public String listarMascotas(Model model) {
        List<PerfilMascota> mascotas = perfilMascotaRepository.findAll(); // Cambia esto según cómo obtienes las mascotas
        model.addAttribute("mascotas", mascotas);
        return "listarMascotas";
    }

    @DeleteMapping("/eliminarMascota/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        try {
            perfilMascotaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Manejar errores si es necesario
            return ResponseEntity.badRequest().build();
        }
    }



    @GetMapping("/formularioMascota")
    public String mostrarFormulario(Model model) {
        List<Raza> razas = razaRepository.findAll();
        List<Tamano> tamanos = tamanoRepository.findAll();
        List<DatosPropietario> propietarios = datosPropietarioRepository.findAll();

        PerfilMascota mascota = new PerfilMascota();
        model.addAttribute("mascota", mascota);
        model.addAttribute("razas", razas);
        model.addAttribute("tamanos", tamanos);
        model.addAttribute("propietarios", propietarios);

        return "formularioMascota";
    }


    @PostMapping("/guardarMascota")
    public String guardarMascota(@ModelAttribute PerfilMascota mascota,
                                 @RequestParam("alergias") String alergias,
                                 @ModelAttribute InformacionGeneral informacionGeneral) {

        // Guardar las entidades relacionadas primero
        DatosInternamiento datosInternamiento = mascota.getDatosInternamiento();
        datosInternamientoRepository.save(datosInternamiento);

        // Crear una instancia de InformacionGeneral y asignar los datos
        InformacionGeneral informacionGeneralMascota = mascota.getInformacionGeneral();
        informacionGeneralRepository.save(informacionGeneralMascota);

        // Dividir la cadena de alergias en alergias individuales
        String[] alergiasArray = alergias.split(",\\s*");

        // Crear una lista para almacenar las alergias individuales
        List<String> alergiasList = Arrays.asList(alergiasArray);

        // Asignar la lista de alergias a la mascota
        mascota.setAlergias(alergiasList);

        perfilMascotaRepository.save(mascota);
        return "redirect:/api/mascotas/formularioMascota";
    }


    public PerfilMascotaController(PerfilMascotaRepository perfilMascotaRepository, RazaRepository razaRepository, TamanoRepository tamanoRepository) {
        this.perfilMascotaRepository = perfilMascotaRepository;
        this.razaRepository = razaRepository;
        this.tamanoRepository = tamanoRepository;
    }


    @GetMapping
    public ResponseEntity<List<PerfilMascota>> obtenerTodosLosPerfilesMascotas() {
        List<PerfilMascota> perfilesMascotas = perfilMascotaRepository.findAll();
        return new ResponseEntity<>(perfilesMascotas, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PerfilMascota> obtenerPerfilMascotaPorId(@PathVariable Long id) {
        Optional<PerfilMascota> perfilMascota = perfilMascotaRepository.findById(id);
        return perfilMascota.map(mascota -> new ResponseEntity<>(mascota, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> agregarPerfilMascota(@RequestBody PerfilMascota perfilMascota) {
        try {
            // Obtén la raza seleccionada por el usuario
            Raza razaSeleccionada = razaRepository.findById(perfilMascota.getRaza().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Raza no encontrada"));

            // Obtén el tamaño seleccionado por el usuario
            Tamano tamanoSeleccionado = tamanoRepository.findById(perfilMascota.getTamano().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tamano no encontrado"));

            // Asigna la raza y el tamaño al perfil de mascota
            perfilMascota.setRaza(razaSeleccionada);
            perfilMascota.setTamano(tamanoSeleccionado);

            PerfilMascota nuevoPerfilMascota = perfilMascotaRepository.save(perfilMascota);
            return new ResponseEntity<>(nuevoPerfilMascota, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPerfilMascota(@PathVariable Long id, @RequestBody PerfilMascota perfilMascota) {
        if (!perfilMascotaRepository.existsById(id)) {
            return new ResponseEntity<>("Perfil de mascota no encontrado", HttpStatus.NOT_FOUND);
        }

        perfilMascota.setId(id);
        PerfilMascota perfilActualizado = perfilMascotaRepository.save(perfilMascota);
        return new ResponseEntity<>(perfilActualizado, HttpStatus.OK);
    }

}
