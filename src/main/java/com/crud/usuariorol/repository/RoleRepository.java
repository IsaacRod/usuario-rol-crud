package com.crud.usuariorol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crud.usuariorol.model.Role;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    // ✅ Buscar por nombre exacto
    Optional<Role> findByNombre(String nombre);
    
    // ✅ Verificar si existe un rol por nombre
    boolean existsByNombre(String nombre);
}