package com.crud.usuariorol.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TUSUARIO")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "A_PATERNO", nullable = false, length = 50)
    private String aPaterno;  // ← Mantenemos con mayúsculas
    
    @Column(name = "A_MATERNO", length = 50)
    private String aMaterno;  // ← Mantenemos con mayúsculas
    
    @Column(name = "EMAIL", unique = true, length = 100)
    private String email;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_roles",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    
    // Constructores
    public Usuario() {}
    
    public Usuario(String nombre, String aPaterno, String aMaterno, String email) {
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.email = email;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    // ¡IMPORTANTE! @JsonProperty en los GETTERS
    @JsonProperty("aPaterno")
    public String getAPaterno() {
        return aPaterno;
    }
    
    public void setAPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }
    
    @JsonProperty("aMaterno")
    public String getAMaterno() {
        return aMaterno;
    }
    
    public void setAMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Set<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    // Método helper para agregar roles
    public void agregarRole(Role role) {
        this.roles.add(role);
        role.getUsuarios().add(this);
    }
    
    // Método helper para remover roles
    public void removerRole(Role role) {
        this.roles.remove(role);
        role.getUsuarios().remove(this);
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", aPaterno='" + aPaterno + '\'' +
                ", aMaterno='" + aMaterno + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles.size() +
                '}';
    }
}