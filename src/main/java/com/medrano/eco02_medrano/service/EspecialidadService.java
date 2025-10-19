package com.medrano.eco02_medrano.service;

import com.medrano.eco02_medrano.entity.Especialidad;

import java.util.List;

public interface EspecialidadService {
    List<Especialidad> listar();
    Especialidad buscar(Integer id);
    Especialidad guardar(Especialidad e);
    void eliminar(Integer id);
}