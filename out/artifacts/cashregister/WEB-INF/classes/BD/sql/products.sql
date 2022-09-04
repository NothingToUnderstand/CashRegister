create table products
(
    id       int auto_increment
        primary key,
    name     varchar(100)    not null,
    quantity int unsigned    not null,
    weight   double unsigned not null,
    price    double unsigned not null,
    img      longblob        not null
);

