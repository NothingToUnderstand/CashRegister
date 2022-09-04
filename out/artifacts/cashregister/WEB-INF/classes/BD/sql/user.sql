create table user
(
    id         int auto_increment
        primary key,
    first_name varchar(15) not null,
    last_name  varchar(15) not null,
    full_name  varchar(50) as (concat(`first_name`, _utf8mb3' ', `last_name`)),
    password   blob        not null,
    sole       blob        not null,
    email      varchar(50) not null,
    constraint full_name
        unique (full_name),
    constraint full_name_2
        unique (full_name),
    constraint full_name_3
        unique (full_name)
);

