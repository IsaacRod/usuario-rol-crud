package com.crud.usuariorol.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RootController {
    
    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Bienvenido a la API de Usuario-Rol CRUD");
        response.put("version", "1.0.0");
        response.put("status", "activo");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("usuarios", "/api/usuarios");
        endpoints.put("roles", "/api/roles");
        endpoints.put("h2-console", "/h2-console");
        endpoints.put("health", "/health");
        
        response.put("endpoints", endpoints);
        
        Map<String, String> database = new HashMap<>();
        database.put("type", "H2");
        database.put("url", "jdbc:h2:mem:usuariodb");
        database.put("username", "sa");
        database.put("password", "(vacío)");
        
        response.put("database", database);
        
        return response;
    }
    
    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Usuario-Rol CRUD API");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return response;
    }
}