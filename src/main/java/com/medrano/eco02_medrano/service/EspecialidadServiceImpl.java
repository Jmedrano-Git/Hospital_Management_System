package com.medrano.eco02_medrano.service;

import com.medrano.eco02_medrano.entity.Especialidad;
import com.medrano.eco02_medrano.repository.EspecialidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    private final EspecialidadRepository repo;

    public EspecialidadServiceImpl(EspecialidadRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Especialidad> listar() { return repo.findAll(); }

    @Override
    public Especialidad buscar(Integer id) { return repo.findById(id).orElse(null); }

    @Override
    public Especialidad guardar(Especialidad e) { return repo.save(e); }

    @Override
    public void eliminar(Integer id) { repo.deleteById(id); }
}