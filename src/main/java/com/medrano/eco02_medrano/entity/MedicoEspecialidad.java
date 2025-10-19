package com.medrano.eco02_medrano.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "medico_especialidad")
public class MedicoEspecialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico_esp")
    private Integer idMedicoEsp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_especialidad", nullable = false)
    private Especialidad especialidad;

    public Integer getIdMedicoEsp() { return idMedicoEsp; }
    public void setIdMedicoEsp(Integer idMedicoEsp) { this.idMedicoEsp = idMedicoEsp; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }
}