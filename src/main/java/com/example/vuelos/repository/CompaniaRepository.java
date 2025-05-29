package com.example.vuelos.repository;

import com.example.vuelos.model.Compania;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompaniaRepository extends JpaRepository<Compania, Integer> {

    //Comprobar si existen vuelos asociados a una compañia
    @Query("select count (v) > 0 from Vuelo v where v.compania.idcompania = :idcompania")
    boolean existsVuelosByCompaniaId(@Param("idcompania") Integer idcompania);

}
