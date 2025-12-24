package com.crud.usuariorol.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.crud.usuariorol.repository.RoleRepository;
import com.crud.usuariorol.model.Role;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    
    private final RoleRepository roleRepository;
    
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    // ✅ Obtener todos los roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    
    // ✅ Obtener role por ID (sin warnings)
    public Role getRoleById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        
        Optional<Role> roleOptional = roleRepository.findById(id);
        
        if (roleOptional.isEmpty()) {
            throw new RuntimeException("Rol no encontrado con ID: " + id);
        }
        
        return roleOptional.get();
    }
    
    // ✅ Obtener role por nombre
    public Role getRoleByNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        
        Optional<Role> roleOptional = roleRepository.findByNombre(nombre);
        
        return roleOptional.orElseThrow(() -> 
            new RuntimeException("Rol no encontrado con nombre: " + nombre)
        );
    }
    
    // ✅ Verificar si existe un role por nombre
    public boolean existeRolePorNombre(String nombre) {
        return roleRepository.existsByNombre(nombre);
    }
    
    // ✅ Crear role
    @Transactional
    public Role crearRole(Role role) {
        // Validar nombre único
        if (roleRepository.existsByNombre(role.getNombre())) {
            throw new RuntimeException("Ya existe un rol con el nombre: " + role.getNombre());
        }
        
        return roleRepository.save(role);
    }
    
    // ✅ Actualizar role
    @Transactional
    public Role actualizarRole(Long id, Role roleActualizado) {
        Role roleExistente = getRoleById(id);
        
        // Verificar si el nombre cambió y si ya existe
        if (!roleExistente.getNombre().equals(roleActualizado.getNombre()) &&
            roleRepository.existsByNombre(roleActualizado.getNombre())) {
            throw new RuntimeException("Ya existe un rol con el nombre: " + roleActualizado.getNombre());
        }
        
        // Actualizar campos
        roleExistente.setNombre(roleActualizado.getNombre());
        roleExistente.setDescripcion(roleActualizado.getDescripcion());
        
        return roleRepository.save(roleExistente);
    }
    
    // ✅ Eliminar role
    @Transactional
    public void eliminarRole(Long id) {
        Role role = getRoleById(id);
        
        // Verificar si el rol tiene usuarios asignados
        if (!role.getUsuarios().isEmpty()) {
            throw new RuntimeException("No se puede eliminar el rol porque tiene usuarios asignados");
        }
        
        roleRepository.delete(role);
    }
}