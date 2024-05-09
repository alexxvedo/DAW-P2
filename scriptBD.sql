-- Crear la base de datos
CREATE DATABASE minitienda;
USE minitienda;

-- Crear la tabla de usuarios
CREATE TABLE usuarios (
  id INT AUTO_INCREMENT PRIMARY KEY,
  correo_electronico VARCHAR(255) NOT NULL,
  contrasena VARCHAR(255) NOT NULL,
  tipo_tarjeta VARCHAR(50) NOT NULL,
  numero_tarjeta VARCHAR(50) NOT NULL
);

-- Crear la tabla de pedidos
CREATE TABLE pedidos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_usuario INT NOT NULL,
  importe_final DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);