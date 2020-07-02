/* PRACTICA SSW 2019-2020
"ANYFEST"
SCRIPT DE INICIALIZACION DE LA BASE DE DATOS
AUTORES:
GARRIDO GARCIA, MIGUEL
IVANOV MANOV, ISTALIYAN
MONTERO VEGA, SILVIA DEL CARMEN
ROBLES DEL BLANCO, MARIA
*/

/* DROPS */
DROP TABLE RESENA;
DROP TABLE SOLICITUD;
DROP TABLE ELEMENTOCHECKLIST;
DROP TABLE PROVEEDOR;
DROP TABLE EVENTO;
DROP TABLE CLIENTE;

/* INICIALIZACION */


CREATE TABLE CLIENTE(
    usuario VARCHAR(21) not null,
    passwd VARCHAR(31) not null,
    nombre VARCHAR(21) not null,
    apellidos VARCHAR(51) not null,
    email VARCHAR(51) not null,
    imagen BLOB,
    primary key (usuario)
);

CREATE TABLE PROVEEDOR(
    usuario VARCHAR(21) not null,
    passwd VARCHAR(31) not null,
    nombre VARCHAR(50) not null,
    email VARCHAR(51) not null,
    telefono VARCHAR(15) not null,
    categoriaProveedor ENUM
    ('dj',
    'catering',
    'floristeria',
    'transporte',
    'animacion') NOT NULL,
    descripcion VARCHAR(101),
    imagen BLOB,
    primary key (usuario)
);

CREATE TABLE EVENTO(
    id VARCHAR(9) not null,
    titulo VARCHAR(21) not null,
    direccion VARCHAR(51) not null,
    fecha DATE not null,
    hora TIME not null,
    categoriaEvento ENUM
    ('reunion_formal',
    'cumpleanos_infantil',
    'cumpleanos',
    'boda',
    'ceremonia_religiosa',
    'graduacion',
    'despedida_soltero') NOT NULL,
    presupuesto DOUBLE not null,
    cliente VARCHAR(21) not null,
    primary key (id),
    foreign key (cliente) REFERENCES CLIENTE(usuario)
);

CREATE TABLE ELEMENTOCHECKLIST (
    id VARCHAR(9) not null,
    aceptado BOOLEAN not null,
    categoriaProveedor ENUM
    ('dj',
    'catering',
    'floristeria',
    'transporte',
    'animacion') NOT NULL,
    evento VARCHAR(9) not null,
    primary key (id),
    foreign key (evento) REFERENCES EVENTO(id)
);

CREATE TABLE SOLICITUD(
    evento VARCHAR(9) not null,
    proveedor VARCHAR(21) not null,
    aceptado BOOLEAN,
    pendiente BOOLEAN not null,
    primary key (evento,proveedor),
    foreign key (evento) REFERENCES EVENTO(id),
    foreign key (proveedor) REFERENCES PROVEEDOR(usuario)
);

CREATE TABLE RESENA(
    proveedor varchar(21) not null,
    fechaHora TIMESTAMP not null,
    valor INT not null,
    evento varchar(21) not null,
    primary key (proveedor,fechaHora),
    foreign key (proveedor) REFERENCES PROVEEDOR(usuario),
    foreign key (evento) REFERENCES EVENTO(id)
);

/* POBLACION */
INSERT INTO CLIENTE VALUES(
    'monica','perro','Mónica','Peral','monicap@gmail.com',NULL
);

INSERT INTO CLIENTE VALUES(
    'paco','tacos','Antonio José','Martínez','pacoman@gmx.de',NULL
);

INSERT INTO CLIENTE VALUES(
    'iban','gato','Ibán','Pan','i-ban@monster.com',NULL
);

INSERT INTO CLIENTE VALUES(
    'isma','kebab','Isma','Antonios','isma-ant@gmail.com',NULL
);

INSERT INTO CLIENTE VALUES(
    'jorge','vasco','Jorge','Wamba','jbcd@apache.org',NULL
);

INSERT INTO CLIENTE VALUES(
    'javi','alaparato','Javier','Méndez','javimz@github.io',NULL
);

INSERT INTO CLIENTE VALUES(
    'mario','peli','Mario','Red','ruedasviejas@protonmail.com',NULL
);

INSERT INTO CLIENTE VALUES(
    'dani','nook','Dani','Bzk','muhprivacy@dbz.es',NULL
);

INSERT INTO CLIENTE VALUES(
    'juan','4321','Juan','Diego','jdsixnine@icloud.com',NULL
);

INSERT INTO CLIENTE VALUES(
    'david','dacia','Davidu','Gomez','davidukiller@gmail.com',NULL
);

INSERT INTO CLIENTE VALUES(
    'mateo','audi','Mateus','Klauss','matius@abt.com',NULL
);

INSERT INTO EVENTO values(
    '00000001','Cumple de Mónica','C/ Hoyo, nº 5, Valladolid',
    '2020-05-21','20:30','cumpleanos',100,'monica'
);

INSERT INTO EVENTO values(
    '00000002','Cumple de Paco','C/ Poyo, nº 5, Valladolid',
    '2020-05-23','20:30','cumpleanos',100,'monica'
);

INSERT INTO EVENTO values(
    '00000003','Boda de Izan','Avenida Puertobonito, Valencia',
    '2020-04-25','16:00','boda',10000,'monica'
);

INSERT INTO EVENTO values (
    '384c1ad','religiosa 2020','Paseo de Belen 15',
    '2020-05-31','22:22:00','ceremonia_religiosa',1780,'david'

);

INSERT INTO ELEMENTOCHECKLIST values(
    '00000001',FALSE,'catering','00000001'
);

INSERT INTO ELEMENTOCHECKLIST values(
    '00000002',FALSE,'dj','00000001'
);

INSERT INTO ELEMENTOCHECKLIST values(
    'c7c3c08',FALSE,'floristeria','384c1ad'
);

INSERT INTO PROVEEDOR VALUES (
    'cateringsricos','pass','Caterings Ricos','cateringsricos@cateringsricos.com',
    '983232323','catering','Somos un cátering muy rico, pruébanos',NULL
);

INSERT INTO PROVEEDOR VALUES (
    'caterings2','pass2','Caterings Pobres','cateringspobres@cateringsricos.com',
    '983232323','catering','Somos la copia de un catering',NULL
);

INSERT INTO PROVEEDOR VALUES (
    'cateringsricos3','pass','Caterings Ricos3','cateringsricos2@cateringsricos.com',
    '983232323','catering','Somos la copia de un catering',NULL
);

INSERT INTO PROVEEDOR VALUES (
    'cateringsricos4','pass','Caterings Ricos4','cateringsricos3@cateringsricos.com',
    '983232323','catering','Somos la copia de un catering',NULL
);

INSERT INTO PROVEEDOR VALUES (
    'floristapaqui','paqui','Floristeria Paqui','ceo@floripaqui.com',
    '983232323','floristeria','Flores coloridas para toda la familia',NULL
);

INSERT INTO PROVEEDOR VALUES (
    'djnano','nano','DJ Nano','booking@djnano.com',
    '983836323','dj','Set Oro viejo',NULL
);

INSERT INTO PROVEEDOR VALUES (
    'krusty','krusty','Krusty','krustyklown@fiction.com',
    '973839323','animacion','Payasadas',NULL
);

/*  evento, proveedor, aceptado */
INSERT INTO SOLICITUD VALUES (
    '00000001','cateringsricos',NULL,TRUE
);

INSERT INTO SOLICITUD VALUES (
    '00000002','cateringsricos',NULL,TRUE
);

INSERT INTO RESENA VALUES (
    'cateringsricos','2020-01-01 10:00:01',5,'00000001'
);

INSERT INTO RESENA VALUES (
    'caterings2','2020-01-01 10:00:01',5,'00000001'
);

INSERT INTO RESENA VALUES (
    'cateringsricos3','2020-01-06 10:00:01',3,'00000001'
);

INSERT INTO RESENA VALUES (
    'cateringsricos4','2020-01-06 10:00:01',3,'00000001'
);

INSERT INTO RESENA VALUES (
    'floristapaqui','2020-01-06 10:00:01',3,'00000001'
);

INSERT INTO RESENA VALUES (
    'djnano','2020-01-06 10:00:01',3,'00000001'
);

INSERT INTO RESENA VALUES (
    'krusty','2020-01-06 10:00:01',3,'00000001'
);