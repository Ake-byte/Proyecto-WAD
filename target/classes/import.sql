INSERT INTO Categoria(nombre_Categoria,descripcion_Categoria,foto) VALUES('Nintendo Switch','Juego TV y portátil','');
INSERT INTO Categoria(nombre_Categoria,descripcion_Categoria,foto) VALUES('Nintendo Switch','Juego TV y portátil','');


INSERT INTO Producto(nombre_Producto,descripcion_Producto,precio,cantidad,categoria_id) VALUES('Fire Emblem 3H','Descubre Fodlan',1699,10,1);

INSERT INTO users (username, password, enabled) VALUES('akemaster','$2y$12$WtTxXIkJ8Udvxk0eW5NmbeLxnBIeeRA6nRv7nUeSKMUBfhniJGmye ',1);
INSERT INTO users (username, password, enabled) VALUES('administrador','$2y$12$KU3OFJflnTz.ARn88rGa1.265K2Sl7uQaxS0T.knweLXbfJHMDVqG ',1);

INSERT INTO authorities (user_id, authority) VALUES (1,'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (2,'ROLE_ADMIN');
