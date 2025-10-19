package com.medrano.eco02_medrano.controller;

import com.medrano.eco02_medrano.entity.HistoriaClinica;
import com.medrano.eco02_medrano.entity.AntecedenteMedico;
import com.medrano.eco02_medrano.repository.AntecedenteMedicoRepository;
import com.medrano.eco02_medrano.service.HistoriaClinicaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/historias")
public class HistoriaClinicaController {

    private final HistoriaClinicaService historiaService;
    private final AntecedenteMedicoRepository antecedenteRepo;

    public HistoriaClinicaController(HistoriaClinicaService historiaService, AntecedenteMedicoRepository antecedenteRepo) {
        this.historiaService = historiaService;
        this.antecedenteRepo = antecedenteRepo;
    }

    // ✅ Mostrar lista de antecedentes (con layout)
    @GetMapping("/{idPaciente}")
    public String verHistoria(@PathVariable Integer idPaciente, Model model) {
        HistoriaClinica historia = historiaService.obtenerPorPaciente(idPaciente);
        if (historia == null)
            historia = historiaService.crearHistoriaParaPaciente(idPaciente);

        model.addAttribute("historia", historia);
        model.addAttribute("antecedentes", historia.getAntecedentes());
        model.addAttribute("content", "listarAntecedentes :: content"); // 💡 Aquí se especifica el fragmento
        return "layout"; // 💡 Renderiza dentro del layout principal
    }

    // ✅ Formulario de nuevo antecedente
    @GetMapping("/{idHistoria}/nuevo")
    public String nuevoAntecedente(@PathVariable Integer idHistoria, Model model) {
        AntecedenteMedico antecedente = new AntecedenteMedico();
        antecedente.setHistoriaClinica(new HistoriaClinica());
        antecedente.getHistoriaClinica().setIdHistoria(idHistoria);

        model.addAttribute("antecedente", antecedente);
        model.addAttribute("content", "formAntecedente :: content"); // 💡 También se inyecta el form en layout
        return "layout";
    }

    // ✅ Guardar antecedente
    @PostMapping("/guardarAntecedente")
    public String guardar(@ModelAttribute AntecedenteMedico antecedente) {
        antecedenteRepo.save(antecedente);
        return "redirect:/historias/" + antecedente.getHistoriaClinica().getIdHistoria();
    }
}
