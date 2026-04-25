package com.mundial.app.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mundial.app.Entidades.Asociacion;
import com.mundial.app.Repositorio.AsociacionRepositorio;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/asociacion")
public class AsociacionControlador {

    @Autowired
    private AsociacionRepositorio repo;

    @GetMapping
    public String listar(Model model, @RequestParam(defaultValue = "0") int page) {

        Page<Asociacion> pagina = repo.findAll(PageRequest.of(page, 5));

        model.addAttribute("lista", pagina.getContent());
        model.addAttribute("totalPages", pagina.getTotalPages());
        model.addAttribute("currentPage", page);

        return "asociacion/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        return "asociacion/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Asociacion asociacion,
                          BindingResult result,
                          RedirectAttributes flash) {

        if(result.hasErrors()){
            return "asociacion/form";
        }

        repo.save(asociacion);
        flash.addFlashAttribute("success", "Asociación guardada ✅");
        return "redirect:/asociacion";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        model.addAttribute("asociacion", repo.findById(id).orElse(new Asociacion()));
        return "asociacion/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, RedirectAttributes flash) {
        repo.deleteById(id);
        flash.addFlashAttribute("error", "Asociación eliminada ❌");
        return "redirect:/asociacion";
    }
}