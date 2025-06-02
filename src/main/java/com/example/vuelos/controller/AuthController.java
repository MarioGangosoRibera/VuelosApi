package com.example.vuelos.controller;

import com.example.vuelos.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> login) {
        String username = login.get("username");
        String password = login.get("password");

        Integer result = jdbc.queryForObject(
                "SELECT COUNT(*) FROM Usuarios WHERE username = ? AND password = SHA2(?, 256)",
                new Object[]{username, password},
                Integer.class
        );

        System.out.println("Login intent: user=" + username + ", result=" + result);

        if (result != null && result == 1) {
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}
