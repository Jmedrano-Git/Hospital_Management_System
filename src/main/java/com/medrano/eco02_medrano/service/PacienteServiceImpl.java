package com.medrano.eco02_medrano.service;

import com.medrano.eco02_medrano.entity.Paciente;
import com.medrano.eco02_medrano.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository repo;

    public PacienteServiceImpl(PacienteRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Paciente> listar() {
        return repo.findAll();
    }

    @Override
    public void grabar(Paciente paciente) {
        repo.save(paciente);
    }

    @Override
    public Paciente buscar(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void desactivar(Integer id) {
        Paciente p = buscar(id);
        if (p != null) {
            p.setEstado("inactivo");
            repo.save(p);
        }
    }
}
