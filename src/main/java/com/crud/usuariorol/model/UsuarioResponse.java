package com.crud.usuariorol.model;

import lombok.Data;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private String email;
    private Set<RoleResponse> roles;
    
    public static UsuarioResponse fromEntity(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setAPaterno(usuario.getAPaterno());
        response.setAMaterno(usuario.getAMaterno());
        response.setEmail(usuario.getEmail());
        
        if (usuario.getRoles() != null) {
            response.setRoles(
                usuario.getRoles().stream()
                    .map(RoleResponse::fromEntity)
                    .collect(Collectors.toSet())
            );
        }
        
        return response;
    }
}