create table users_roles
(
    id_user int not null,
    id_role int not null,
    primary key (id_user, id_role),
    constraint id_user
        unique (id_user),
    constraint users_roles_ibfk_1
        foreign key (id_role) references roles (id)
            on update cascade on delete cascade,
    constraint users_roles_ibfk_2
        foreign key (id_user) references user (id)
            on update cascade on delete cascade
);

create index id_role
    on users_roles (id_role);

