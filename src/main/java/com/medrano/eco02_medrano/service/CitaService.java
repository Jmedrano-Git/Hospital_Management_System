package com.medrano.eco02_medrano.service;

import com.medrano.eco02_medrano.entity.Cita;

import java.util.List;

public interface CitaService {
    List<Cita> listar();
    Cita buscar(Integer id);
    void guardar(Cita c);
    void eliminar(Integer id);
    void cambiarEstado(Integer id, String estado); // programada|atendida|cancelada
}