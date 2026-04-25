package com.mundial.app.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mundial.app.Entidades.*;
import com.mundial.app.Repositorio.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/club")
public class ClubControlador {

    @Autowired private ClubRepositorio clubRepo;
    @Autowired private AsociacionRepositorio asoRepo;
    @Autowired private EntrenadorRepositorio entRepo;
    @Autowired private JugadorRepositorio jugRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", clubRepo.findAll());
        return "club/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("club", new Club());
        cargarRelaciones(model);
        return "club/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Club club) {

        // 🔥 reconstruir ENTRENADOR
        if (club.getEntrenador() != null && club.getEntrenador().getId() != null) {
            club.setEntrenador(
                entRepo.findById(club.getEntrenador().getId()).orElse(null)
            );
        }

        // 🔥 reconstruir ASOCIACION
        if (club.getAsociacion() != null && club.getAsociacion().getId() != null) {
            club.setAsociacion(
                asoRepo.findById(club.getAsociacion().getId()).orElse(null)
            );
        }

        // 🔥 reconstruir JUGADORES
        if (club.getJugadores() != null) {
            club.setJugadores(
                club.getJugadores().stream()
                    .map(j -> jugRepo.findById(j.getId()).orElse(null))
                    .collect(Collectors.toList())
            );
        }

        clubRepo.save(club);
        return "redirect:/club";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        model.addAttribute("club", clubRepo.findById(id).orElse(new Club()));
        cargarRelaciones(model);
        return "club/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        clubRepo.deleteById(id);
        return "redirect:/club";
    }

    private void cargarRelaciones(Model model){
        model.addAttribute("asociaciones", asoRepo.findAll());
        model.addAttribute("entrenadores", entRepo.findAll());
        model.addAttribute("jugadores", jugRepo.findAll());
    }
}
