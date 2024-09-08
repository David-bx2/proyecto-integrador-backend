package com.clinica.odontologia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Entity
public class Odontologo {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    private String nombre;
    @Setter
    @Getter
    private String apellido;
    @Setter
    @Getter
    private String matricula;

    @OneToMany(mappedBy = "odontologo")
    private List<Turno> turnos;

    // Getters, setters y constructores

    public Odontologo(String matricula, String apellido, String nombre, Long id, List<Turno> turnos) {
        this.matricula = matricula;
        this.apellido = apellido;
        this.nombre = nombre;
        this.id = id;
        this.turnos = turnos;
    }

    public Odontologo() {

    }


}


