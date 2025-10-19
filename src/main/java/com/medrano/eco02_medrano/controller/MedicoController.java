package com.medrano.eco02_medrano.controller;

import com.medrano.eco02_medrano.entity.Medico;
import com.medrano.eco02_medrano.service.EspecialidadService;
import com.medrano.eco02_medrano.service.MedicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService service;
    private final EspecialidadService espService;

    public MedicoController(MedicoService service, EspecialidadService espService) {
        this.service = service;
        this.espService = espService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("title", "Médicos");
        model.addAttribute("medicos", service.listar());
        model.addAttribute("content", "listarMedicos :: content");
        return "layout";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("title", "Nuevo Médico");
        model.addAttribute("medico", new Medico());
        model.addAttribute("especialidades", espService.listar());
        model.addAttribute("seleccionadas", List.of());
        model.addAttribute("content", "formMedico :: content");
        return "layout";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Medico m = service.buscar(id);
        if (m == null) return "redirect:/medicos";

        model.addAttribute("title", "Editar Médico");
        model.addAttribute("medico", m);
        model.addAttribute("especialidades", espService.listar());
        // IDs ya asociados
        var ids = m.getMedicoEspecialidades().stream()
                .map(me -> me.getEspecialidad().getIdEspecialidad())
                .toList();
        model.addAttribute("seleccionadas", ids);
        model.addAttribute("content", "formMedico :: content");
        return "layout";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("medico") Medico medico,
                          @RequestParam(value = "especialidadesIds", required = false) List<Integer> especialidadesIds) {
        Medico saved = service.guardar(medico);
        service.actualizarEspecialidades(saved.getIdMedico(), especialidadesIds);
        return "redirect:/medicos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return "redirect:/medicos";
    }
}