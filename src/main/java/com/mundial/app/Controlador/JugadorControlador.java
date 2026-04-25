package com.mundial.app.Controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mundial.app.Entidades.Jugador;
import com.mundial.app.Repositorio.JugadorRepositorio;

import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/jugador")
public class JugadorControlador {

    @Autowired
    private JugadorRepositorio repo;

    @GetMapping
    public String listar(Model model, @RequestParam(defaultValue = "0") int page) {

        Page<Jugador> pagina = repo.findAll(PageRequest.of(page, 5));

        model.addAttribute("lista", pagina.getContent());
        model.addAttribute("totalPages", pagina.getTotalPages());
        model.addAttribute("currentPage", page);

        return "jugador/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        return "jugador/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Jugador jugador,
                          BindingResult result,
                          RedirectAttributes flash) {

        if(result.hasErrors()){
            return "jugador/form";
        }

        repo.save(jugador);
        flash.addFlashAttribute("success", "Jugador guardado correctamente ✅");
        return "redirect:/jugador";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        model.addAttribute("jugador", repo.findById(id).orElse(new Jugador()));
        return "jugador/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, RedirectAttributes flash) {
        repo.deleteById(id);
        flash.addFlashAttribute("error", "Jugador eliminado ❌");
        return "redirect:/jugador";
    }
}
