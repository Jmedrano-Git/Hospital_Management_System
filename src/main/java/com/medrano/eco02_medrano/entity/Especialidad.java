package com.medrano.eco02_medrano.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "especialidad")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidad")
    private Integer idEspecialidad;

    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MedicoEspecialidad> medicoEspecialidades = new LinkedHashSet<>();

    // Getters/Setters
    public Integer getIdEspecialidad() { return idEspecialidad; }
    public void setIdEspecialidad(Integer idEspecialidad) { this.idEspecialidad = idEspecialidad; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Set<MedicoEspecialidad> getMedicoEspecialidades() { return medicoEspecialidades; }
    public void setMedicoEspecialidades(Set<MedicoEspecialidad> medicoEspecialidades) { this.medicoEspecialidades = medicoEspecialidades; }
}