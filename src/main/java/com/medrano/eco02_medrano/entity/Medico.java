package com.medrano.eco02_medrano.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Integer idMedico;

    private String nombres;

    private String apellidos;

    private String colegiatura;

    private String telefono;

    private String correo;

    private String estado = "activo"; // activo | inactivo

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MedicoEspecialidad> medicoEspecialidades = new LinkedHashSet<>();

    // Getters/Setters
    public Integer getIdMedico() { return idMedico; }
    public void setIdMedico(Integer idMedico) { this.idMedico = idMedico; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getColegiatura() { return colegiatura; }
    public void setColegiatura(String colegiatura) { this.colegiatura = colegiatura; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Set<MedicoEspecialidad> getMedicoEspecialidades() { return medicoEspecialidades; }
    public void setMedicoEspecialidades(Set<MedicoEspecialidad> medicoEspecialidades) { this.medicoEspecialidades = medicoEspecialidades; }
}