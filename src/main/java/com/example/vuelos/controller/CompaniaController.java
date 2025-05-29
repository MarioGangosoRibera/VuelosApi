package com.example.vuelos.controller;

import com.example.vuelos.model.Compania;
import com.example.vuelos.model.Vuelo;
import com.example.vuelos.repository.CompaniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companias")
public class CompaniaController {

    private final CompaniaRepository companiaRepository;

    @Autowired
    public CompaniaController(CompaniaRepository companiaRepository){
        this.companiaRepository = companiaRepository;
    }

    @GetMapping("/todas")
    public List<Compania> obtenerTodasLascompanias(){
        return companiaRepository.findAll();
    }

    @PostMapping("/registrar")
    public Compania registrarCompania(@RequestBody Compania compania){
        return companiaRepository.save(compania);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCompania(@PathVariable Integer id){
        Optional<Compania> companiaOptional = companiaRepository.findById(id);
        if(companiaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Compañia con ID " + id + " no encontrada");
        }

        boolean tieneVuelos = companiaRepository.existsVuelosByCompaniaId(id);
        if (tieneVuelos){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar la compañia porque tiene vuelos asociados");
        }
        companiaRepository.deleteById(id);
        return ResponseEntity.ok("Compañia eliminada");
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> modificarNombreCompania(@PathVariable Integer id, @RequestBody String nuevoNombre) {
        return companiaRepository.findById(id).map(compania -> {
            compania.setNombrecompania(nuevoNombre);
            companiaRepository.save(compania);
            return ResponseEntity.ok("Nombre de la compañía actualizado correctamente.");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compañía no encontrada."));
    }
}
