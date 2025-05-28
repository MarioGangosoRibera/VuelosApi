package com.example.vuelos.controller;

import com.example.vuelos.model.Vuelo;
import com.example.vuelos.repository.VueloRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vuelos")
public class VueloController {

    private final VueloRepository vueloRepository;

    public VueloController(VueloRepository vueloRepository){
        this.vueloRepository = vueloRepository;
    }

    /*
    @GetMapping("/buscar")
    public List<Vuelo> buscarVuelos(
            @RequestParam(required = false) String origen,
            @RequestParam(required = false) String destino,
            @RequestParam(required = false) String numeroescalas){
        return vueloRepository.buscarVuelos(origen, destino, Integer.valueOf(numeroescalas));
    }
     */

    @GetMapping("/buscar")
    public List<Vuelo> buscarVuelos(
            @RequestParam String origen,
            @RequestParam String destino,
            @RequestParam Integer numEscalas
    ){
        return vueloRepository.findByOrigenAndDestinoAndNumeroescalas(origen, destino, numEscalas);
    }
}
