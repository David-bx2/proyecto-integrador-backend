package com.clinica.odontologia.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;

    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    // Getters, setters y constructores


    public Turno(Long id, LocalDateTime fecha, Odontologo odontologo, Paciente paciente) {
        this.id = id;
        this.fecha = fecha;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }

    public Turno() {}

    public Long getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
