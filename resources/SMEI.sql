create database SMEI;

use SMEI;


create table rol (
    idRol int not null auto_increment,
    primary key (idRol),
    nombre varchar(20)
);

create table usuario (
    idUsuario int not null auto_increment,
    primary key (idUsuario),
    idRol int,
    foreign key (idRol)
        references rol (idRol),
    nombre varchar(50),
    password varchar(20),
    identificacion varchar(20),
    email varchar(30),
    telefono varchar(20),
    habilitado boolean
);

create table espacios (
    idEspacio int not null auto_increment,
    primary key (idEspacio),
    nombre varchar(30),
    capacidadDePersonas int,
    descripcion varchar(200),
    habilitado boolean
);

create table reservaciones (
    idReservacion int not null auto_increment,
    primary key (idReservacion),
    idUsuario int,
    foreign key (idUsuario)
        references usuario (idUsuario),
    idEspacio int,
    foreign key (idEspacio)
        references espacios (idEspacio),
    fechaCreacion datetime,
    fechaModificacion datetime,
    fechaInicio datetime,
    fechaFin datetime
);

-- alter table espacios add column descripcion varchar(200);

select 
    *
from
    espacios;