INSERT INTO Categoria(nombre_Categoria,descripcion_Categoria,foto) VALUES('Nintendo Switch','Juego TV y portátil Versión 1.0 Negro','');
INSERT INTO Categoria(nombre_Categoria,descripcion_Categoria,foto) VALUES('Nintendo Switch','Juego TV y portátil Versión 1.0 Neon','');
INSERT INTO Categoria(nombre_Categoria,descripcion_Categoria,foto) VALUES('Nintendo Switch','Juego TV y portátil Versión 1.1 Negro','');
INSERT INTO Categoria(nombre_Categoria,descripcion_Categoria,foto) VALUES('Nintendo Switch','Juego TV y portátil Versión 1.1 Neon','');
INSERT INTO Categoria(nombre_Categoria,descripcion_Categoria,foto) VALUES('Nintendo Switch','Juego TV y portátil Versión Animal Crossing','');
INSERT INTO Categoria(nombre_Categoria,descripcion_Categoria,foto) VALUES('Nintendo Switch Lite','Juego portátil Turquesa','');
INSERT INTO Categoria(nombre_Categoria,descripcion_Categoria,foto) VALUES('Nintendo Switch Lite','Juego portátil Amarillo','');
INSERT INTO Categoria(nombre_Categoria,descripcion_Categoria,foto) VALUES('Nintendo Switch Lite','Juego portátil Gris','');
INSERT INTO Categoria(nombre_Categoria,descripcion_Categoria,foto) VALUES('Nintendo Switch Lite','Juego portátil Rosa','');

INSERT INTO Producto(nombre_Producto,descripcion_Producto,precio,cantidad,categoria_id,foto) VALUES('Fire Emblem 3H','Descubre Fodlan',1699,10,1,'');
INSERT INTO Producto(nombre_Producto,descripcion_Producto,precio,cantidad,categoria_id,foto) VALUES('Dragon Quest XI','Unete a Heroe en su aventura por heliodor',1699,10,1,'');
INSERT INTO Producto(nombre_Producto,descripcion_Producto,precio,cantidad,categoria_id,foto) VALUES('Mario Kart 8 Deluxe','Mario Carreras',1699,10,1,'');
INSERT INTO Producto(nombre_Producto,descripcion_Producto,precio,cantidad,categoria_id,foto) VALUES('Super Smash Bros Ultimate','Mario Peleas',1699,10,1,'');
INSERT INTO Producto(nombre_Producto,descripcion_Producto,precio,cantidad,categoria_id,foto) VALUES('Pokemon Shield','Inicia tu aventura por Galar',1699,10,1,'');
INSERT INTO Producto(nombre_Producto,descripcion_Producto,precio,cantidad,categoria_id,foto) VALUES('Pokemon Sword','Inicia tu aventura por Galar',1699,10,1,'');


INSERT INTO users (username, password, enabled) VALUES('akemaster','$2y$12$WtTxXIkJ8Udvxk0eW5NmbeLxnBIeeRA6nRv7nUeSKMUBfhniJGmye ',1);
INSERT INTO users (username, password, enabled) VALUES('administrador','$2y$12$KU3OFJflnTz.ARn88rGa1.265K2Sl7uQaxS0T.knweLXbfJHMDVqG ',1);

INSERT INTO authorities (user_id, authority) VALUES (1,'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (2,'ROLE_ADMIN');
