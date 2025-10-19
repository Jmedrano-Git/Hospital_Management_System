package com.medrano.eco02_medrano.entity;

import jakarta.persistence.*;

@Entity
public class AntecedenteMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAntecedente;

    private String tipo;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idHistoria")
    private HistoriaClinica historiaClinica;

    // Getters y setters
    public Integer getIdAntecedente() { return idAntecedente; }
    public void setIdAntecedente(Integer idAntecedente) { this.idAntecedente = idAntecedente; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public HistoriaClinica getHistoriaClinica() { return historiaClinica; }
    public void setHistoriaClinica(HistoriaClinica historiaClinica) { this.historiaClinica = historiaClinica; }
}