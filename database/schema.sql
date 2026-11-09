-- Esquema completo; ejecutar conectado a MySQL como usuario con permiso CREATE.
CREATE DATABASE IF NOT EXISTS consultas_medicas_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE consultas_medicas_db;
CREATE TABLE IF NOT EXISTS roles (
 id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 nombre VARCHAR(30) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS usuarios (
 id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 username VARCHAR(50) NOT NULL UNIQUE,
 password VARCHAR(100) NOT NULL,
 rol_id BIGINT NOT NULL,
 CONSTRAINT fk_usuario_rol FOREIGN KEY (rol_id) REFERENCES roles(id)
);
CREATE TABLE IF NOT EXISTS medicos (
 id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 nombre VARCHAR(80) NOT NULL,
 especialidad VARCHAR(80) NOT NULL,
 telefono VARCHAR(20),
 usuario_id BIGINT UNIQUE,
 CONSTRAINT fk_medico_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
CREATE TABLE IF NOT EXISTS pacientes (
 id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 nombre VARCHAR(40) NOT NULL,
 usuario_id BIGINT UNIQUE,
 CONSTRAINT fk_paciente_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
CREATE TABLE IF NOT EXISTS consultas (
 id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 motivo VARCHAR(100) NOT NULL,
 consultorio INT NOT NULL,
 hora_inicio TIME NOT NULL,
 hora_fin TIME NOT NULL,
 paciente_id BIGINT NOT NULL,
 medico_id BIGINT NOT NULL,
 CONSTRAINT fk_consulta_paciente FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
 CONSTRAINT fk_consulta_medico FOREIGN KEY (medico_id) REFERENCES medicos(id),
 CONSTRAINT chk_consulta_consultorio CHECK (consultorio > 0)
);
