create table client
(
    id serial not null
        constraint client_pk
            primary key,
    name varchar(50),
    lastname varchar(50),
    address varchar(255),
    company varchar(50)
);

alter table client owner to example1;

INSERT INTO client(name, lastname, address, company) VALUES ('client1', 'lastname1', 'address1', 'company1');
INSERT INTO client(name, lastname, address, company) VALUES ('client2', 'lastname2', 'address2', 'company2');
INSERT INTO client(name, lastname, address, company) VALUES ('client3', 'lastname3', 'address3', 'company1');