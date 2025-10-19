package com.medrano.eco02_medrano.controller;

import com.medrano.eco02_medrano.entity.Paciente;
import com.medrano.eco02_medrano.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    // 🏠 Ruta para mostrar Dashboard
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Dashboard");
        model.addAttribute("content", "dashboard :: content");
        return "layout";
    }

    // 📋 Listar pacientes
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("title","Pacientes");
        model.addAttribute("pacientes", service.listar());
        model.addAttribute("content","listarPacientes :: content");
        return "layout";
    }

    // ➕ Nuevo paciente
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("title","Nuevo Paciente");
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("content","formPaciente :: content");
        return "layout";
    }

    // 💾 Guardar paciente
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Paciente paciente) {
        service.guardar(paciente);
        return "redirect:/pacientes";
    }

    // ✏️ Editar paciente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("title","Editar Paciente");
        model.addAttribute("paciente", service.buscarPorId(id));
        model.addAttribute("content","formPaciente :: content");
        return "layout";
    }

    // ❌ Eliminar paciente
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return "redirect:/pacientes";
    }
}
