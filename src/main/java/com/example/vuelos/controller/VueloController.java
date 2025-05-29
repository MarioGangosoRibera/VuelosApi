package com.example.vuelos.controller;

import com.example.vuelos.model.Vuelo;
import com.example.vuelos.repository.VueloRepository;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/todos")
    public List<Vuelo> obtenerTodosLosVuelos(){
        return vueloRepository.findAll();
    }

    //Metodo para buscar pero pasanado los campos por la url ?origen=MADRID&destino=LOS%20ANGELES&numEscalas=3
    @GetMapping("/buscar")
    public List<Vuelo> buscarVuelos(
            @RequestParam String origen,
            @RequestParam String destino,
            @RequestParam Integer numEscalas
    ){
        return vueloRepository.findByOrigenAndDestinoAndNumeroescalas(origen, destino, numEscalas);
    }

    ///api/vuelos/buscar-por-destino?destino=Madrid
    @GetMapping("/buscar-por-destino")
    public List<Vuelo> buscarPorDestino(@RequestParam String destino){
        return vueloRepository.findByDestino(destino);
    }

    ///api/vuelos/contar-por-origen?origen=Paris
    @GetMapping("/contar-por-origen")
    public Long contarVuelosPorOrigen(@RequestParam String origen){
        return vueloRepository.countByOrigen(origen);
    }


}
