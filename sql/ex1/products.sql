create table products
(
    id serial not null
        constraint products_pkey
            primary key,
    name varchar(50),
    category varchar(50),
    price numeric
);

alter table products owner to example1;


INSERT INTO products(name, category, price) VALUES ('computer','electronics',499.90);
INSERT INTO products(name, category, price) VALUES ('bike','sport',880.99);
INSERT INTO products(name, category, price) VALUES ('book','home',15);
INSERT INTO products(name, category, price) VALUES ('laptop','electronics',299.90);
INSERT INTO products(name, category, price) VALUES ('telephone','electronics',199.90);
INSERT INTO products(name, category, price) VALUES ('ball','sport',9.90);
INSERT INTO products(name, category, price) VALUES ('lamp','home',12.30);