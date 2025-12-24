package com.crud.usuariorol.model;

import java.util.HashSet;
import java.util.Set;

public class UsuarioRequest {
    
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private String email;
    private Set<Long> roleIds = new HashSet<>();
    
    // Constructor vacío
    public UsuarioRequest() {}
    
    // Constructor con parámetros
    public UsuarioRequest(String nombre, String aPaterno, String aMaterno, String email) {
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.email = email;
    }
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getAPaterno() {
        return aPaterno;
    }
    
    public void setAPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }
    
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
    
    public Set<Long> getRoleIds() {
        return roleIds;
    }
    
    public void setRoleIds(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }
    
    @Override
    public String toString() {
        return "UsuarioRequest{" +
                "nombre='" + nombre + '\'' +
                ", aPaterno='" + aPaterno + '\'' +
                ", aMaterno='" + aMaterno + '\'' +
                ", email='" + email + '\'' +
                ", roleIds=" + roleIds +
                '}';
    }
}