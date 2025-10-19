package com.medrano.eco02_medrano.service;

import com.medrano.eco02_medrano.entity.Paciente;
import com.medrano.eco02_medrano.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository repo;

    public PacienteServiceImpl(PacienteRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> listar() {
        return repo.findAll();
    }

    @Override
    @Transactional
    public void guardar(Paciente p) {
        repo.save(p);
    }

    @Override
    @Transactional(readOnly = true)
    public Paciente buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
