package com.crud.usuariorol.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.crud.usuariorol.repository.UsuarioRepository;
import com.crud.usuariorol.repository.RoleRepository;
import com.crud.usuariorol.model.Usuario;
import com.crud.usuariorol.model.Role;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    
    public UsuarioService(UsuarioRepository usuarioRepository, RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
    }
    
    // ✅ Obtener todos los usuarios
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
    
    // ✅ Obtener usuario por ID (sin warnings)
    public Usuario getUsuarioById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        
        if (usuarioOptional.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        
        return usuarioOptional.get();
    }
    
    // ✅ Obtener usuario por email
    public Usuario getUsuarioByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        
        return usuarioOptional.orElseThrow(() -> 
            new RuntimeException("Usuario no encontrado con email: " + email)
        );
    }
    
    // ✅ Buscar por nombre (ACTUALIZADO)
    public List<Usuario> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return usuarioRepository.findAll();
        }
        return usuarioRepository.buscarPorNombre(nombre); // ← CAMBIADO
    }
    
    // ✅ Buscar por apellido paterno (ACTUALIZADO)
    public List<Usuario> buscarPorAPaterno(String aPaterno) {
        if (aPaterno == null || aPaterno.trim().isEmpty()) {
            return usuarioRepository.findAll();
        }
        return usuarioRepository.buscarPorAPaterno(aPaterno); // ← CAMBIADO
    }
    
    // ✅ Buscar por apellido materno (ACTUALIZADO)
    public List<Usuario> buscarPorAMaterno(String aMaterno) {
        if (aMaterno == null || aMaterno.trim().isEmpty()) {
            return usuarioRepository.findAll();
        }
        return usuarioRepository.buscarPorAMaterno(aMaterno); // ← CAMBIADO
    }
    
    // ✅ Búsqueda general (sin cambios)
    public List<Usuario> buscarGeneral(String termino) {
        if (termino == null || termino.trim().isEmpty()) {
            return usuarioRepository.findAll();
        }
        return usuarioRepository.buscarGeneral(termino);
    }
    
    // ✅ Crear usuario
    @Transactional
    public Usuario crearUsuario(Usuario usuario) {
        // Validar email único
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El email ya está registrado: " + usuario.getEmail());
        }
        
        return usuarioRepository.save(usuario);
    }
    
    // ✅ Actualizar usuario
    @Transactional
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = getUsuarioById(id);
        
        // Verificar si el email cambió y si ya existe
        if (!usuarioExistente.getEmail().equals(usuarioActualizado.getEmail()) &&
            usuarioRepository.existsByEmail(usuarioActualizado.getEmail())) {
            throw new RuntimeException("El email ya está registrado: " + usuarioActualizado.getEmail());
        }
        
        // Actualizar campos
        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setAPaterno(usuarioActualizado.getAPaterno());
        usuarioExistente.setAMaterno(usuarioActualizado.getAMaterno());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        
        return usuarioRepository.save(usuarioExistente);
    }
    
    // ✅ Eliminar usuario (CORREGIDO - sin warnings)
    @Transactional
    public void eliminarUsuario(Long id) {
        // Validar que el id no sea nulo
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        
        // Verificar si existe usando el método que ya validamos
        getUsuarioById(id); // Esto lanzará excepción si no existe
        
        // Ahora eliminar (sin warning porque ya validamos que id no es null)
        usuarioRepository.deleteById(id);
    }
    
    // ✅ Asignar roles a usuario (CORREGIDO - sin warnings)
    @Transactional
    public Usuario asignarRoles(Long usuarioId, Set<Long> roleIds) {
        // Validar que usuarioId no sea nulo
        if (usuarioId == null) {
            throw new IllegalArgumentException("El ID de usuario no puede ser nulo");
        }
        
        // Validar que roleIds no sea nulo
        if (roleIds == null) {
            throw new IllegalArgumentException("La lista de roles no puede ser nula");
        }
        
        Usuario usuario = getUsuarioById(usuarioId);
        Set<Role> roles = new HashSet<>();
        
        for (Long roleId : roleIds) {
            // Validar que cada roleId no sea nulo
            if (roleId == null) {
                throw new IllegalArgumentException("El ID de rol no puede ser nulo");
            }
            
            // Buscar el rol (sin warning porque ya validamos que no es null)
            Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + roleId));
            roles.add(role);
        }
        
        usuario.setRoles(roles);
        return usuarioRepository.save(usuario);
    }
    
    // ✅ Obtener usuarios por rol
    public List<Usuario> getUsuariosPorRol(String nombreRol) {
        return usuarioRepository.findByRolNombre(nombreRol);
    }
}