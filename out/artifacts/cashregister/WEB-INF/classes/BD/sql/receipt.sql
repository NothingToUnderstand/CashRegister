create table receipt
(
    id                 int auto_increment
        primary key,
    cashier_id         int                                 not null,
    cashier_name       varchar(50)                         not null,
    number_of_products int                                 null,
    total_sum          double                              null,
    open_date_time     timestamp default CURRENT_TIMESTAMP null,
    close_date_time    timestamp                           null
);

