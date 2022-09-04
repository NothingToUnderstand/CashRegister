create table products_in_archive_receipt
(
    id_receipt        int    not null,
    id_product        int    not null,
    amount_of_product int    not null,
    total_sum         double not null,
    primary key (id_receipt, id_product),
    constraint products_in_archive_receipt_ibfk_1
        foreign key (id_receipt) references archive_receipt (id)
            on update cascade on delete cascade,
    constraint products_in_archive_receipt_ibfk_2
        foreign key (id_product) references products (id)
            on update cascade on delete cascade
);

create index id_product
    on products_in_archive_receipt (id_product);

