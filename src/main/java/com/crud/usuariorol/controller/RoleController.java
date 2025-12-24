package com.crud.usuariorol.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crud.usuariorol.service.RoleService;
import com.crud.usuariorol.model.Role;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RoleController {
    
    private final RoleService roleService;
    
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    
    // ✅ 1. Obtener todos los roles
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
    
    // ✅ 2. Obtener role por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        try {
            Role role = roleService.getRoleById(id);
            return ResponseEntity.ok(role);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(crearError(e.getMessage()));
        }
    }
    
    // ✅ 3. Obtener role por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> getRoleByNombre(@PathVariable String nombre) {
        try {
            Role role = roleService.getRoleByNombre(nombre);
            return ResponseEntity.ok(role);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(crearError(e.getMessage()));
        }
    }
    
    // ✅ 4. Verificar si existe role
    @GetMapping("/existe/{nombre}")
    public ResponseEntity<Map<String, Boolean>> existeRole(@PathVariable String nombre) {
        boolean existe = roleService.existeRolePorNombre(nombre);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("existe", existe);
        return ResponseEntity.ok(respuesta);
    }
    
    // ✅ 5. Crear role
    @PostMapping
    public ResponseEntity<?> crearRole(@RequestBody Role role) {
        try {
            Role nuevoRole = roleService.crearRole(role);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRole);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(crearError(e.getMessage()));
        }
    }
    
    // ✅ 6. Actualizar role
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarRole(@PathVariable Long id, @RequestBody Role role) {
        try {
            Role roleActualizado = roleService.actualizarRole(id, role);
            return ResponseEntity.ok(roleActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(crearError(e.getMessage()));
        }
    }
    
    // ✅ 7. Eliminar role
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRole(@PathVariable Long id) {
        try {
            roleService.eliminarRole(id);
            return ResponseEntity.ok(crearMensaje("Rol eliminado exitosamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(crearError(e.getMessage()));
        }
    }
    
    // ✅ 8. Health check
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Role API");
        return ResponseEntity.ok(response);
    }
    
    // Métodos helper para respuestas
    private Map<String, String> crearError(String mensaje) {
        Map<String, String> error = new HashMap<>();
        error.put("error", mensaje);
        error.put("timestamp", java.time.LocalDateTime.now().toString());
        return error;
    }
    
    private Map<String, String> crearMensaje(String mensaje) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("message", mensaje);
        respuesta.put("timestamp", java.time.LocalDateTime.now().toString());
        return respuesta;
    }
}