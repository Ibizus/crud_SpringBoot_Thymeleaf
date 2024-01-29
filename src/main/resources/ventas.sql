DROP DATABASE IF EXISTS ventas;
CREATE DATABASE ventas CHARACTER SET utf8mb4;
USE ventas;

CREATE TABLE cliente (
                         id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         apellido1 VARCHAR(100) NOT NULL,
                         apellido2 VARCHAR(100),
                         email VARCHAR(100),
                         ciudad VARCHAR(100),
                         categoría INT UNSIGNED
);

CREATE TABLE comercial (
                           id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(100) NOT NULL,
                           apellido1 VARCHAR(100) NOT NULL,
                           apellido2 VARCHAR(100),
                           comisión DECIMAL(4,3)
);

CREATE TABLE pedido (
                        id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                        total DOUBLE NOT NULL,
                        fecha DATE,
                        id_cliente INT UNSIGNED NOT NULL,
                        id_comercial INT UNSIGNED NOT NULL,
                        FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE CASCADE ON UPDATE CASCADE,
                        FOREIGN KEY (id_comercial) REFERENCES comercial(id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO cliente VALUES(1, 'Aarón', 'Rivero', 'Gómez', 'correo@hotmail.com', 'Almería', 100);
INSERT INTO cliente VALUES(2, 'Adela', 'Salas', 'Díaz', 'correo@yahoo.com', 'Granada', 200);
INSERT INTO cliente VALUES(3, 'Adolfo', 'Rubio', 'Flores', 'correo@gmail.com', 'Sevilla', NULL);
INSERT INTO cliente VALUES(4, 'Adrián', 'Suárez', NULL, 'correo@gmail.com', 'Jaén', 300);
INSERT INTO cliente VALUES(5, 'Marcos', 'Loyola', 'Méndez', 'correo@hotmail.com', 'Almería', 200);
INSERT INTO cliente VALUES(6, 'María', 'Santana', 'Moreno', 'correo@gmail.com', 'Cádiz', 100);
INSERT INTO cliente VALUES(7, 'Pilar', 'Ruiz', NULL, 'correo@yahoo.com', 'Sevilla', 300);
INSERT INTO cliente VALUES(8, 'Pepe', 'Ruiz', 'Santana', 'correo@hotmail.com', 'Huelva', 200);
INSERT INTO cliente VALUES(9, 'Guillermo', 'López', 'Gómez', 'correo@gmail.com', 'Granada', 225);
INSERT INTO cliente VALUES(10, 'Daniel', 'Santana', 'Loyola', 'correo@yahoo.com', 'Sevilla', 125);

INSERT INTO comercial VALUES(1, 'Daniel', 'Sáez', 'Vega', 0.55);
INSERT INTO comercial VALUES(2, 'Juan', 'Gómez', 'López', 0.333);
INSERT INTO comercial VALUES(3, 'Diego','Flores', 'Salas', 0.276);
INSERT INTO comercial VALUES(4, 'Marta','Herrera', 'Gil', 0.65);
INSERT INTO comercial VALUES(5, 'Antonio','Carretero', 'Ortega', 0.422);
INSERT INTO comercial VALUES(6, 'Manuel','Domínguez', 'Hernández', 0.299);
INSERT INTO comercial VALUES(7, 'Antonio','Vega', 'Hernández', 0.512);
INSERT INTO comercial VALUES(8, 'Alfredo','Ruiz', 'Flores', 0.3);

INSERT INTO pedido VALUES(1, 150.5, '2023-11-05', 5, 2);
INSERT INTO pedido VALUES(2, 270.65, '2021-09-10', 1, 5);
INSERT INTO pedido VALUES(3, 65.26, '2023-10-05', 2, 1);
INSERT INTO pedido VALUES(4, 110.5, '2019-08-17', 8, 3);
INSERT INTO pedido VALUES(5, 948.5, '2017-09-10', 5, 2);
INSERT INTO pedido VALUES(6, 2400.6, '2021-07-27', 7, 1);
INSERT INTO pedido VALUES(7, 5760, '2023-12-10', 2, 1);
INSERT INTO pedido VALUES(8, 1983.43, '2017-10-10', 4, 6);
INSERT INTO pedido VALUES(9, 2480.4, '2020-10-10', 8, 3);
INSERT INTO pedido VALUES(10, 250.45, '2022-06-27', 8, 2);
INSERT INTO pedido VALUES(11, 75.29, '2024-01-17', 3, 7);
INSERT INTO pedido VALUES(12, 3045.6, '2023-12-28', 2, 1);
INSERT INTO pedido VALUES(13, 545.75, '2023-02-25', 6, 1);
INSERT INTO pedido VALUES(14, 145.82, '2020-03-02', 6, 1);
INSERT INTO pedido VALUES(15, 370.85, '2024-01-05', 1, 5);
INSERT INTO pedido VALUES(16, 2389.23, '2019-03-11', 1, 5);