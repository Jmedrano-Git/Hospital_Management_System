package com.medrano.eco02_medrano.service;

import com.medrano.eco02_medrano.entity.Paciente;
import java.util.List;

public interface PacienteService {
    List<Paciente> listar();
    void grabar(Paciente paciente);
    Paciente buscar(Integer id);
    void desactivar(Integer id);
}
