CREATE TABLE humans(
id INT auto_increment primary key,
name VARCHAR(50) NOT NULL,
last_name VARCHAR(20) NOT NULL,
age INT
);

INSERT INTO humans (name, last_name, age) VALUES ('Adrian', 'Perez', 22);
INSERT INTO humans (name, last_name, age) VALUES ('Alejandra', 'Armenta', 12);
INSERT INTO humans (name, last_name, age) VALUES ('Lorenzo', 'Dolores', 56);
INSERT INTO humans (name, last_name, age) VALUES ('Guadalupe', 'Guillen', 76);
INSERT INTO humans (name, last_name, age) VALUES ('Alfredo', 'Garcia', 27);