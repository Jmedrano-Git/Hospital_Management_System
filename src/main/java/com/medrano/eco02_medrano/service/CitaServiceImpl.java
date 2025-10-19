package com.medrano.eco02_medrano.service;

import com.medrano.eco02_medrano.entity.Cita;
import com.medrano.eco02_medrano.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository repo;

    public CitaServiceImpl(CitaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Cita> listar() {
        return repo.findAll();
    }

    @Override
    public Cita buscar(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Cita cita) {
        repo.save(cita);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public void cambiarEstado(Integer id, String estado) {
        Cita c = buscar(id);
        if (c != null) {
            c.setEstado(estado);
            repo.save(c);
        }
    }
}
