package com.medrano.eco02_medrano.service;

import com.medrano.eco02_medrano.entity.Consulta;
import com.medrano.eco02_medrano.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository repo;

    public ConsultaServiceImpl(ConsultaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Consulta> listar() {
        return repo.findAll();
    }

    @Override
    public Consulta guardar(Consulta c) {
        return repo.save(c);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
