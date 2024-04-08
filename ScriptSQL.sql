CREATE TABLE tiendas(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(255) NOT NULL,
    ubicacion varchar(255) NOT NULL
);

CREATE TABLE productos(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(255) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    id_tienda INT,
	CONSTRAINT fk_id_tienda FOREIGN KEY(id_tienda) REFERENCES tiendas(id) ON DELETE CASCADE
);

CREATE TABLE clientes(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(255) NOT NULL,
    apellido varchar(255) NOT NULL,
    email varchar(255) NOT NULL
);

CREATE TABLE compras(
	id INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT,
	CONSTRAINT fk_id_cliente FOREIGN KEY(id_cliente) REFERENCES clientes(id) ON DELETE CASCADE,
    id_producto INT,
	CONSTRAINT fk_id_producto FOREIGN KEY(id_producto) REFERENCES productos(id) ON DELETE CASCADE,
    fecha_compra DATE NOT NULL,
    cantidad INT NOT NULL 
);

INSERT INTO tiendas(nombre, ubicacion) VALUES
('Tienda1','Floresta'),
('Tienda2','Estadio'),
('Tienda3','Centro')



