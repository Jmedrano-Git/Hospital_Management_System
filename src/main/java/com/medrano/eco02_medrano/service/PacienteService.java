package com.medrano.eco02_medrano.service;

import com.medrano.eco02_medrano.entity.Paciente;
import java.util.List;

public interface PacienteService {
    List<Paciente> listar();
    void guardar(Paciente p);
    Paciente buscarPorId(Integer id);
    void eliminar(Integer id);
}
