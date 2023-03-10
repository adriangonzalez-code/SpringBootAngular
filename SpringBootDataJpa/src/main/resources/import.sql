INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Blanca', 'Martinez', 'juan@mail.com', '2018-06-12', '02f1221f-3a73-499d-8a75-1876e6cd573b_85bbz9j.jpg');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Cata', 'Perejil', 'juan@mail.com', '2018-06-12', '4bc567a1-ba2e-4649-bfdf-4cd7f26c821f_3_shot00019.jpg');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Juana', 'De Arco', 'juan@mail.com', '2018-06-12', '39d4b21a-7f48-48b2-a312-bb6c3cc24096_doom3_logo.jpg');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Tomás', 'De Aquino', 'juan@mail.com', '2018-06-12', '0319ccf3-7fc9-4795-9bb5-cc6ceebfd2f4_85bbz9j.jpg');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Aristóteles', 'De Atenas', 'juan@mail.com', '2018-06-12', '373808d8-c22f-4716-ba34-bcbb1a0675ce_2005_doom_wallpaper_001.jpg');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Leucipo', 'Matacarrillo', 'juan@mail.com', '2018-06-12', 'a88c0784-d7ed-41d3-b910-037df7551863_469881_20040805_screen002.jpg');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Demócrito', 'De Alejandría', 'juan@mail.com', '2018-06-12', 'cadc481f-4b58-4644-bab5-d92bd313c273_3_shot00026.jpg');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Marco', 'Aurelio', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Félix', 'Cuevas', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Juan', 'De Arimatea', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Maria', 'San Juan', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('John', 'Doe', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Hugo', 'Sanchez', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Jorge', 'Campos', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Jared', 'Borgetti', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Francisco', 'Fonseca', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Juan', 'Pérez', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Ilena', 'Hiridia', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Platón', 'De Atenas', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Gregorio', 'Magno', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Ines', 'De La Cruz', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Javier', 'Hernández', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Carlos', 'Vela', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Miguel', 'Hidalgo', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Pancho', 'Villa', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Juan', 'Pérez', 'juan@mail.com', '2018-06-12', '');
INSERT INTO clientes(nombre, apellido, email, create_at, foto) VALUES('Gustavo', 'A. Madero', 'juan@mail.com', '2018-06-12', '');

INSERT INTO productos(nombre, precio, create_at) VALUES('Sony Xperia 10 IV', 8125, NOW());
INSERT INTO productos(nombre, precio, create_at) VALUES('Sony Xperia 5 III', 18357, NOW());
INSERT INTO productos(nombre, precio, create_at) VALUES('Samsung A53', 7354, NOW());
INSERT INTO productos(nombre, precio, create_at) VALUES('Samsung A03', 2678, NOW());
INSERT INTO productos(nombre, precio, create_at) VALUES('Fiio F15', 2541, NOW());
INSERT INTO productos(nombre, precio, create_at) VALUES('Audifonos Fiio', 1987, NOW());
INSERT INTO productos(nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos(nombre, precio, create_at) VALUES('Sony Cámara Digital', 123490, NOW());
INSERT INTO productos(nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos(nombre, precio, create_at) VALUES('Sony Notebook', 374990, NOW());
INSERT INTO productos(nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());
INSERT INTO productos(nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());
INSERT INTO facturas_item (cantidad, factura_id, producto_id) VALUES(1, 1, 10);
INSERT INTO facturas_item (cantidad, factura_id, producto_id) VALUES(2, 1, 7);
INSERT INTO facturas_item (cantidad, factura_id, producto_id) VALUES(1, 1, 8);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_item (cantidad, factura_id, producto_id) VALUES(3, 2, 12);


INSERT INTO users (username, password, enabled) VALUES('adrian', '$2a$10$UIfJKqDziWvIM/SrNG9uZuUAJqXXJ948lNrwIU/ypV6hoo9lQJFsm', 1);
INSERT INTO users (username, password, enabled) VALUES('admin', '$2a$10$8wINk/HgNLM.8WndA7.f6eJ0i5bIGVBgMoCblEv2LvABeS5jk2m/C', 1);

INSERT INTO authorities (user_id, authority) VALUES(1, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES(2, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES(2, 'ROLE_ADMIN');