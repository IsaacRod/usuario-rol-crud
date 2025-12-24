-- Limpiar tablas primero
DELETE FROM usuario_roles;
DELETE FROM tusuario;
DELETE FROM trole;

-- Insertar roles
INSERT INTO trole (id, nombre, descripcion) VALUES 
(1, 'ADMIN', 'Administrador del sistema'),
(2, 'USER', 'Usuario regular'),
(3, 'EDITOR', 'Editor de contenido');

-- Insertar usuarios
INSERT INTO tusuario (id, nombre, a_paterno, a_materno, email) VALUES 
(1, 'Juan', 'Pérez', 'Gómez', 'juan.perez@email.com'),
(2, 'María', 'López', 'Rodríguez', 'maria.lopez@email.com'),
(3, 'Carlos', 'García', 'Sánchez', 'carlos.garcia@email.com');

-- Asignar roles a usuarios
INSERT INTO usuario_roles (usuario_id, role_id) VALUES 
(1, 1), -- Juan es ADMIN
(1, 2), -- Juan también es USER
(2, 2), -- María es USER
(3, 3); -- Carlos es EDITOR

-- Reiniciar secuencias
ALTER TABLE trole ALTER COLUMN id RESTART WITH 4;
ALTER TABLE tusuario ALTER COLUMN id RESTART WITH 4;