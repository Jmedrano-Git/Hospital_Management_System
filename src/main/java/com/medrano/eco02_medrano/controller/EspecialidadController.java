package com.medrano.eco02_medrano.controller;

import com.medrano.eco02_medrano.entity.Especialidad;
import com.medrano.eco02_medrano.service.EspecialidadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/especialidades")
public class EspecialidadController {

    private final EspecialidadService service;

    public EspecialidadController(EspecialidadService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("title", "Especialidades");
        model.addAttribute("especialidades", service.listar());
        model.addAttribute("content", "listarEspecialidades :: content");
        return "layout";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("title", "Nueva Especialidad");
        model.addAttribute("especialidad", new Especialidad());
        model.addAttribute("content", "formEspecialidad :: content");
        return "layout";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        var e = service.buscar(id);
        if (e == null) return "redirect:/especialidades";
        model.addAttribute("title", "Editar Especialidad");
        model.addAttribute("especialidad", e);
        model.addAttribute("content", "formEspecialidad :: content");
        return "layout";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Especialidad especialidad) {
        service.guardar(especialidad);
        return "redirect:/especialidades";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return "redirect:/especialidades";
    }
}