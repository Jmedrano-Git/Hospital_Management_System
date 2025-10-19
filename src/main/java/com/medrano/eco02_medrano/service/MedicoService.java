package com.medrano.eco02_medrano.service;

import com.medrano.eco02_medrano.entity.Medico;

import java.util.List;

public interface MedicoService {
    List<Medico> listar();
    Medico buscar(Integer id);
    Medico guardar(Medico m);
    void eliminar(Integer id);

    void actualizarEspecialidades(Integer idMedico, List<Integer> especialidadIds);
}