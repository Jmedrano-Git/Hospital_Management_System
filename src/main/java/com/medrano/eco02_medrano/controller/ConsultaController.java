package com.medrano.eco02_medrano.controller;

import com.medrano.eco02_medrano.entity.Consulta;
import com.medrano.eco02_medrano.service.ConsultaService;
import com.medrano.eco02_medrano.service.MedicoService;
import com.medrano.eco02_medrano.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;

    public ConsultaController(ConsultaService consultaService,
                              PacienteService pacienteService,
                              MedicoService medicoService) {
        this.consultaService = consultaService;
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("consultas", consultaService.listar());
        return "listarConsultas";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("medicos", medicoService.listar());
        return "nuevaConsulta";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Consulta consulta) {
        consultaService.guardar(consulta);
        return "redirect:/consultas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        consultaService.eliminar(id);
        return "redirect:/consultas";
    }
}