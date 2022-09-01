create table products_in_receipt
(
    id_receipt        int    not null,
    id_product        int    not null,
    amount_of_product int    not null,
    price_for_amount  double not null,
    id                int auto_increment
        primary key,
    constraint products_in_receipt_ibfk_1
        foreign key (id_receipt) references receipt (id)
            on update cascade on delete cascade,
    constraint products_in_receipt_ibfk_2
        foreign key (id_product) references products (id)
            on update cascade on delete cascade
);

