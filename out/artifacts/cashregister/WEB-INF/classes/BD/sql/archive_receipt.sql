create table archive_receipt
(
    id                 int         not null
        primary key,
    cashier_id         int         not null,
    cashier_name       varchar(50) not null,
    number_of_products int         not null,
    total_sum          double      not null,
    open_date_time     timestamp   not null,
    close_date_time    timestamp   not null
);

