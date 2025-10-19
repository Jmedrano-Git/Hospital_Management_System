package com.medrano.eco02_medrano.controller;

import com.medrano.eco02_medrano.entity.Paciente;
import com.medrano.eco02_medrano.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // 📋 Listar todos los pacientes
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("title", "Gestión de Pacientes");
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("content", "listarPacientes :: content"); // carga el fragmento
        return "layout";
    }

    // ➕ Mostrar formulario de nuevo paciente
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("title", "Registrar Paciente");
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("content", "formPaciente :: content");
        return "layout";
    }

    // 💾 Guardar paciente (nuevo o editado)
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("paciente") Paciente paciente,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Registrar Paciente");
            model.addAttribute("content", "formPaciente :: content");
            return "layout";
        }
        pacienteService.grabar(paciente);
        return "redirect:/pacientes";
    }

    // ✏️ Editar paciente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Paciente paciente = pacienteService.buscar(id);
        if (paciente == null) {
            return "redirect:/pacientes";
        }
        model.addAttribute("title", "Editar Paciente");
        model.addAttribute("paciente", paciente);
        model.addAttribute("content", "formPaciente :: content");
        return "layout";
    }

    // ❌ Desactivar paciente
    @GetMapping("/desactivar/{id}")
    public String desactivar(@PathVariable Integer id) {
        pacienteService.desactivar(id);
        return "redirect:/pacientes";
    }
}
