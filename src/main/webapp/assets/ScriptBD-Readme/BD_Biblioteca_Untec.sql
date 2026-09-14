CREATE DATABASE bd_biblioteca_untec
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE bd_biblioteca_untec;

CREATE TABLE usuario(
	id INT AUTO_INCREMENT PRIMARY KEY,
    usuario varchar(50) not null,
    contrasena varchar(15) not null,
    rol varchar(15) not null,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO usuario (usuario, contrasena, rol) VALUES
('roberto@correo.cl','FullStackJava', 'Administrador'),
('christian@correo.cl','Chris123', 'Usuario'),
('admin@correo.cl','Admin1234', 'Administrador');

select * from usuario;

CREATE TABLE libro(
	id_libro INT auto_increment primary key,
    nombre_libro varchar(50) not null,
    autor_libro varchar(50) not null,
    editorial varchar(30) not null,
    ano_publicacion int not null
);

insert into libro(nombre_libro, autor_libro, editorial, ano_publicacion)
values ('El último Deseo', 'Andrzej Sapkowski', 'Alamut Ediciones', 2009),
('La Sangre de los Elfos', 'Andrzej Sapkowski', 'Artifex', 2018),
('Tiempo de Odio', 'Andrzej Sapkowski', 'Artifex', 2016),
('La Espada del Destino', 'Andrzej Sapkowski', 'Artifex', 2018),
('El Llamado del Cthulhu', 'H. P. Lovecraft', 'Fondo De Cultura Economica', 2022),
('Colmillo Blanco', 'Jack London', 'Mestas Ediciones', 2016),
('El último Deseo', 'Andrzej Sapkowski', 'Alamut Ediciones', 2009),
('La Sangre de los Elfos', 'Andrzej Sapkowski', 'Artifex', 2018),
('Tiempo de Odio', 'Andrzej Sapkowski', 'Artifex', 2016),
('La Espada del Destino', 'Andrzej Sapkowski', 'Artifex', 2018),
('El Llamado del Cthulhu', 'H. P. Lovecraft', 'Fondo De Cultura Economica', 2022),
('Colmillo Blanco', 'Jack London', 'Mestas Ediciones', 2016),
('El último Deseo', 'Andrzej Sapkowski', 'Alamut Ediciones', 2009),
('La Sangre de los Elfos', 'Andrzej Sapkowski', 'Artifex', 2018),
('Tiempo de Odio', 'Andrzej Sapkowski', 'Artifex', 2016),
('La Espada del Destino', 'Andrzej Sapkowski', 'Artifex', 2018),
('El Llamado del Cthulhu', 'H. P. Lovecraft', 'Fondo De Cultura Economica', 2022),
('Colmillo Blanco', 'Jack London', 'Mestas Ediciones', 2016); 

select * from libro;

