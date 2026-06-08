-- Crear usuario root
CREATE OR REPLACE USER admin@localhost IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON  *.* to 'admin'@'localhost';

-- CREACIÓN DE BASE DE DATOS
drop database if exists fittrack;
CREATE DATABASE IF NOT EXISTS fittrack;
USE fittrack;


-- TABLA USUARIOS

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    altura DOUBLE NOT NULL,
    password VARCHAR(100) NOT NULL,
    rol VARCHAR(20) NOT NULL DEFAULT 'USER'
);


-- TABLA MUSCULOS

CREATE TABLE musculos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);


-- TABLA EJERCICIOS

CREATE TABLE ejercicios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    dificultad VARCHAR(20),
    musculo_id INT,
    FOREIGN KEY (musculo_id) REFERENCES musculos(id)
);


-- DATOS INICIALES

-- Músculos básicos
INSERT INTO musculos (nombre) VALUES 
('Pecho'),
('Espalda'),
('Piernas'),
('Brazos'),
('Abdomen');

-- Ejercicios de ejemplo
INSERT INTO ejercicios (nombre, descripcion, dificultad, musculo_id) VALUES
('Flexiones', 'Ejercicio de pecho con peso corporal', 'Fácil', 1),
('Press banca', 'Ejercicio con barra para pecho', 'Medio', 1),

('Dominadas', 'Ejercicio de espalda en barra', 'Difícil', 2),
('Remo', 'Ejercicio de espalda con peso', 'Medio', 2),

('Sentadillas', 'Ejercicio básico para piernas', 'Fácil', 3),
('Prensa', 'Ejercicio de piernas en máquina', 'Medio', 3),

('Curl bíceps', 'Ejercicio para brazos con mancuernas', 'Fácil', 4),

('Abdominales', 'Ejercicio para abdomen', 'Fácil', 5);
