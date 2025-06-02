package com.example.vuelos.controller;

import com.example.vuelos.config.JwtUtil;
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
    private final JwtUtil jwtUtil;

    @Autowired
    public CompaniaController(CompaniaRepository companiaRepository, JwtUtil jwtUtil) {
        this.companiaRepository = companiaRepository;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/todas")
    public List<Compania> obtenerTodasLascompanias() {
        return companiaRepository.findAll();
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarCompania(
            @RequestHeader(value = "Authorization", required = false) String auth,
            @RequestBody Compania compania) {
        if (!isAuthorized(auth)) return ResponseEntity.status(403).body("Acceso denegado");
        return ResponseEntity.ok(companiaRepository.save(compania));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCompania(
            @RequestHeader(value = "Authorization", required = false) String auth,
            @PathVariable Integer id) {

        if (!isAuthorized(auth)) return ResponseEntity.status(403).body("Acceso denegado");

        Optional<Compania> companiaOptional = companiaRepository.findById(id);
        if (companiaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Compañía con ID " + id + " no encontrada");
        }

        boolean tieneVuelos = companiaRepository.existsVuelosByCompaniaId(id);
        if (tieneVuelos) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar la compañía porque tiene vuelos asociados");
        }

        companiaRepository.deleteById(id);
        return ResponseEntity.ok("Compañía eliminada");
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> modificarNombreCompania(
            @RequestHeader(value = "Authorization", required = false) String auth,
            @PathVariable Integer id,
            @RequestBody String nuevoNombre) {

        if (!isAuthorized(auth)) return ResponseEntity.status(403).body("Acceso denegado");

        return companiaRepository.findById(id).map(compania -> {
            compania.setNombrecompania(nuevoNombre);
            companiaRepository.save(compania);
            return ResponseEntity.ok("Nombre actualizado");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compañía no encontrada."));
    }

    private boolean isAuthorized(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return false;
        String token = authHeader.substring(7);
        String username = jwtUtil.validateToken(token);
        return username != null && username.equals("root");
    }
}
