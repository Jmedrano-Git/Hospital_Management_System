package com.medrano.eco02_medrano.controller;

import org.springframework.ui.Model;
import com.medrano.eco02_medrano.entity.Cita;
import com.medrano.eco02_medrano.entity.Medico;
import com.medrano.eco02_medrano.entity.Paciente;
import com.medrano.eco02_medrano.repository.MedicoRepository;
import com.medrano.eco02_medrano.repository.PacienteRepository;
import com.medrano.eco02_medrano.service.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/citas")
public class CitaController {

    private final CitaService service;
    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;

    public CitaController(CitaService service,
                          PacienteRepository pacienteRepo,
                          MedicoRepository medicoRepo) {
        this.service = service;
        this.pacienteRepo = pacienteRepo;
        this.medicoRepo = medicoRepo;
    }

    // Listar citas
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("title", "Citas Médicas");
        model.addAttribute("citas", service.listar());
        model.addAttribute("content", "listarCitas :: content");
        return "layout";
    }

    // Crear nueva cita
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("title", "Nueva Cita");
        model.addAttribute("cita", new Cita());
        model.addAttribute("pacientes", pacienteRepo.findAll());
        model.addAttribute("medicos", medicoRepo.findAll());
        model.addAttribute("content", "formCita :: content");
        return "layout";
    }

    // Guardar cita (CORREGIDO)
    @PostMapping("/guardar")
    public String guardar(@RequestParam("paciente") Integer idPaciente,
                          @RequestParam("medico") Integer idMedico,
                          @ModelAttribute("cita") Cita cita) {

        Paciente paciente = pacienteRepo.findById(idPaciente).orElse(null);
        Medico medico = medicoRepo.findById(idMedico).orElse(null);

        cita.setPaciente(paciente);
        cita.setMedico(medico);

        service.guardar(cita);
        return "redirect:/citas";
    }

    // Editar cita
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Cita c = service.buscar(id);
        if (c == null) return "redirect:/citas";
        model.addAttribute("title", "Editar Cita");
        model.addAttribute("cita", c);
        model.addAttribute("pacientes", pacienteRepo.findAll());
        model.addAttribute("medicos", medicoRepo.findAll());
        model.addAttribute("content", "formCita :: content");
        return "layout";
    }

    // Cambiar estado
    @GetMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable Integer id, @RequestParam String estado) {
        service.cambiarEstado(id, estado);
        return "redirect:/citas";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return "redirect:/citas";
    }
}
