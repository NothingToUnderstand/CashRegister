create table receipts_in_report
(
    id_report  int not null,
    id_receipt int not null,
    primary key (id_report, id_receipt),
    constraint receipts_in_report_ibfk_1
        foreign key (id_report) references report (id)
            on update cascade on delete cascade,
    constraint receipts_in_report_ibfk_2
        foreign key (id_receipt) references receipt (id)
            on update cascade on delete cascade
);

create index id_chek
    on receipts_in_report (id_receipt);

