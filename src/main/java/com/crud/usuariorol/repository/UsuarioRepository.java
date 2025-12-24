package com.crud.usuariorol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.crud.usuariorol.model.Usuario;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // ✅ Métodos derivados que SÍ funcionan
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    
    // ✅ Búsqueda por nombre (con @Query explícito)
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Usuario> buscarPorNombre(@Param("nombre") String nombre);
    
    // ✅ Búsqueda por apellido paterno (con @Query explícito)
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.aPaterno) LIKE LOWER(CONCAT('%', :aPaterno, '%'))")
    List<Usuario> buscarPorAPaterno(@Param("aPaterno") String aPaterno);
    
    // ✅ Búsqueda por apellido materno (con @Query explícito)
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.aMaterno) LIKE LOWER(CONCAT('%', :aMaterno, '%'))")
    List<Usuario> buscarPorAMaterno(@Param("aMaterno") String aMaterno);
    
    // ✅ Búsqueda general (nombre, apellido paterno y materno)
    @Query("SELECT u FROM Usuario u WHERE " +
           "LOWER(u.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(u.aPaterno) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(u.aMaterno) LIKE LOWER(CONCAT('%', :termino, '%'))")
    List<Usuario> buscarGeneral(@Param("termino") String termino);
    
    // ✅ Buscar usuarios que tengan un rol específico
    @Query("SELECT u FROM Usuario u JOIN u.roles r WHERE r.nombre = :nombreRol")
    List<Usuario> findByRolNombre(@Param("nombreRol") String nombreRol);
}