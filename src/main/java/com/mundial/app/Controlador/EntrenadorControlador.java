package com.mundial.app.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mundial.app.Entidades.Entrenador;
import com.mundial.app.Repositorio.EntrenadorRepositorio;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/entrenador")
public class EntrenadorControlador {

    @Autowired
    private EntrenadorRepositorio repo;

    @GetMapping
    public String listar(Model model, @RequestParam(defaultValue = "0") int page) {

        Page<Entrenador> pagina = repo.findAll(PageRequest.of(page, 5));

        model.addAttribute("lista", pagina.getContent());
        model.addAttribute("totalPages", pagina.getTotalPages());
        model.addAttribute("currentPage", page);

        return "entrenador/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        return "entrenador/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Entrenador entrenador,
                          BindingResult result,
                          RedirectAttributes flash) {

        if(result.hasErrors()){
            return "entrenador/form";
        }

        repo.save(entrenador);
        flash.addFlashAttribute("success", "Entrenador guardado ✅");
        return "redirect:/entrenador";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        model.addAttribute("entrenador", repo.findById(id).orElse(new Entrenador()));
        return "entrenador/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, RedirectAttributes flash) {
        repo.deleteById(id);
        flash.addFlashAttribute("error", "Entrenador eliminado ❌");
        return "redirect:/entrenador";
    }

@NotBlank(message = "El nombre es obligatorio")
private String nombre;

@NotBlank(message = "El apellido es obligatorio")
private String apellido;
}


