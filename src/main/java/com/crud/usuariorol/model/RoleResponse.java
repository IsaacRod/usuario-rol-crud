package com.crud.usuariorol.model;

import lombok.Data;

@Data
public class RoleResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    
    public static RoleResponse fromEntity(Role role) {
        RoleResponse response = new RoleResponse();
        response.setId(role.getId());
        response.setNombre(role.getNombre());
        response.setDescripcion(role.getDescripcion());
        return response;
    }
}