package com.crud.usuariorol.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crud.usuariorol.service.UsuarioService;
import com.crud.usuariorol.model.Usuario;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    // ✅ 1. Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }
    
    // ✅ 2. Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.getUsuarioById(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(crearError(e.getMessage()));
        }
    }
    
    // ✅ 3. Obtener usuario por email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUsuarioByEmail(@PathVariable String email) {
        try {
            Usuario usuario = usuarioService.getUsuarioByEmail(email);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(crearError(e.getMessage()));
        }
    }
    
    // ✅ 4. Buscar por nombre
    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<Usuario>> buscarPorNombre(@RequestParam(required = false) String nombre) {
        List<Usuario> usuarios = usuarioService.buscarPorNombre(nombre);
        return ResponseEntity.ok(usuarios);
    }
    
    // ✅ 5. Buscar por apellido paterno
    @GetMapping("/buscar/apaterno")
    public ResponseEntity<List<Usuario>> buscarPorAPaterno(@RequestParam(required = false) String apaterno) {
        List<Usuario> usuarios = usuarioService.buscarPorAPaterno(apaterno);
        return ResponseEntity.ok(usuarios);
    }
    
    // ✅ 6. Búsqueda general
    @GetMapping("/buscar")
    public ResponseEntity<List<Usuario>> buscarGeneral(@RequestParam(required = false) String termino) {
        List<Usuario> usuarios = usuarioService.buscarGeneral(termino);
        return ResponseEntity.ok(usuarios);
    }
    
    // ✅ 7. Crear usuario
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(crearError(e.getMessage()));
        }
    }
    
    // ✅ 8. Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(crearError(e.getMessage()));
        }
    }
    
    // ✅ 9. Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok(crearMensaje("Usuario eliminado exitosamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(crearError(e.getMessage()));
        }
    }
    
    // ✅ 10. Asignar roles a usuario
    @PostMapping("/{id}/roles")
    public ResponseEntity<?> asignarRoles(@PathVariable Long id, @RequestBody Set<Long> roleIds) {
        try {
            Usuario usuario = usuarioService.asignarRoles(id, roleIds);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(crearError(e.getMessage()));
        }
    }
    
    // ✅ 11. Obtener usuarios por rol
    @GetMapping("/por-rol/{nombreRol}")
    public ResponseEntity<?> getUsuariosPorRol(@PathVariable String nombreRol) {
        try {
            List<Usuario> usuarios = usuarioService.getUsuariosPorRol(nombreRol);
            return ResponseEntity.ok(usuarios);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(crearError(e.getMessage()));
        }
    }
    
    // ✅ 12. Health check
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Usuario API");
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