package com.crud.usuariorol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsuarioRolCrudApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsuarioRolCrudApplication.class, args);
        System.out.println("\n=========================================");
        System.out.println("✅ API Usuario-Rol CRUD INICIADA CORRECTAMENTE");
        System.out.println("=========================================");
        System.out.println("🌐 URL Principal: http://localhost:8080");
        System.out.println("📊 H2 Console: http://localhost:8080/h2-console");
        System.out.println("👥 Usuarios API: http://localhost:8080/api/usuarios");
        System.out.println("🎯 Roles API: http://localhost:8080/api/roles");
        System.out.println("🔗 JDBC URL: jdbc:h2:mem:usuariodb");
        System.out.println("👤 Usuario: sa | Contraseña: (vacío)");
        System.out.println("=========================================\n");
    }
}