package com.medrano.eco02_medrano.service;

import com.medrano.eco02_medrano.entity.Especialidad;
import com.medrano.eco02_medrano.entity.Medico;
import com.medrano.eco02_medrano.entity.MedicoEspecialidad;
import com.medrano.eco02_medrano.repository.EspecialidadRepository;
import com.medrano.eco02_medrano.repository.MedicoEspecialidadRepository;
import com.medrano.eco02_medrano.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository repo;
    private final EspecialidadRepository espRepo;
    private final MedicoEspecialidadRepository meRepo;

    public MedicoServiceImpl(MedicoRepository repo, EspecialidadRepository espRepo, MedicoEspecialidadRepository meRepo) {
        this.repo = repo;
        this.espRepo = espRepo;
        this.meRepo = meRepo;
    }

    @Override
    public List<Medico> listar() { return repo.findAll(); }

    @Override
    public Medico buscar(Integer id) { return repo.findById(id).orElse(null); }

    @Override
    public Medico guardar(Medico m) { return repo.save(m); }

    @Override
    public void eliminar(Integer id) {
        Medico m = buscar(id);
        if (m != null) {
            meRepo.deleteByMedico(m);
            repo.delete(m);
        }
    }

    @Transactional
    @Override
    public void actualizarEspecialidades(Integer idMedico, List<Integer> especialidadIds) {
        // 1️⃣ Buscar el médico gestionado por Hibernate
        Medico medico = repo.findById(idMedico)
                .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado"));

        // 2️⃣ Primero, desvinculamos manualmente las relaciones actuales
        List<MedicoEspecialidad> actuales = meRepo.findByMedico(medico);
        for (MedicoEspecialidad me : actuales) {
            meRepo.delete(me);
        }

        // 3️⃣ Limpiamos la colección del objeto médico en memoria
        medico.getMedicoEspecialidades().clear();

        // 4️⃣ Si hay nuevas especialidades, las agregamos desde la BD (todas "managed")
        if (especialidadIds != null && !especialidadIds.isEmpty()) {
            for (Integer idEsp : especialidadIds) {
                Especialidad especialidad = espRepo.findById(idEsp).orElse(null);
                if (especialidad != null) {
                    MedicoEspecialidad nuevo = new MedicoEspecialidad();
                    nuevo.setMedico(medico);          // médico managed
                    nuevo.setEspecialidad(especialidad); // especialidad managed
                    meRepo.saveAndFlush(nuevo);        // 👈 fuerza sincronización inmediata
                    medico.getMedicoEspecialidades().add(nuevo);
                }
            }
        }

        // 5️⃣ Guardamos el médico ya con sus relaciones actualizadas
        repo.saveAndFlush(medico);
    }
}