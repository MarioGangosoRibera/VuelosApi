package com.example.vuelos.model;

import javax.persistence.*;

@Entity
@Table(name = "Vuelos")
public class Vuelo {

    @Id
    @Column(name = "idvuelo")
    private String idVuelo;

    @Column(name = "origen")
    private String origen;

    @Column(name = "destino")
    private String destino;

    @Column(name = "precio")
    private Float precio;

    @Column(name = "numeroescalas")
    private Integer numeroescalas;

    @ManyToOne
    @JoinColumn(name = "idcompañia")
    private Compania compania;

    public Vuelo() {
    }

    public String getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(String idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getNumeroescalas() {
        return numeroescalas;
    }

    public void setNumeroescalas(Integer numeroescalas) {
        this.numeroescalas = numeroescalas;
    }

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    }
}
