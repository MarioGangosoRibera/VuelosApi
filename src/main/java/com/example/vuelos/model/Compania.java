package com.example.vuelos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Compañias")
public class Compania {
    @Id
    @Column(name = "idcompañia")
    private Integer idcompania;

    @Column(name = "nombrecompañia")
    private String nombrecompania;

    public Compania() {
    }

    public Integer getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(Integer idcompania) {
        this.idcompania = idcompania;
    }

    public String getNombrecompania() {
        return nombrecompania;
    }

    public void setNombrecompania(String nombrecompania) {
        this.nombrecompania = nombrecompania;
    }
}
