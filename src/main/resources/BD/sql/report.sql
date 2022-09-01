create table report
(
    id                 int auto_increment
        primary key,
    cashier_id         int                                 not null,
    cashier_name       varchar(50)                         not null,
    number_of_receipts int                                 null,
    total_sum          double                              null,
    date_time          timestamp default CURRENT_TIMESTAMP not null,
    z_report           tinyint(1)                          not null
);

