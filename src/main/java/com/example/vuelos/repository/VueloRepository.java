package com.example.vuelos.repository;

import com.example.vuelos.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo, String> {

    List<Vuelo> findByOrigenAndDestinoAndNumeroescalas(String origen, String destino, Integer numeroescalas);


    /*
    @Query("SELECT v FROM Vuelo v WHERE " +
            "(:origen IS NULL OR v.origen = :origen) AND " +
            "(:destino IS NULL OR v.destino = :destino) AND " +
            "(:numEscalas IS NULL OR v.numeroescalas = :numEscalas)")
    List<Vuelo> buscarVuelos(@Param("origen") String origen,
                             @Param("destino") String destino,
                             @Param("numEscalas") Integer numEscalas);

     */

    List<Vuelo> findByDestino(String destino);

    Long countByOrigen(String origen);
}

