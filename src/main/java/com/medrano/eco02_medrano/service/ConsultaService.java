package com.medrano.eco02_medrano.service;

import com.medrano.eco02_medrano.entity.Consulta;

import java.util.List;

public interface ConsultaService {
    List<Consulta> listar();
    Consulta guardar(Consulta c);
    void eliminar(Integer id);
}