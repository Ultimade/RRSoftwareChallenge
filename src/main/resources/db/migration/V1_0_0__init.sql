create sequence hibernate_sequence;


create table IF NOT EXISTS revinfo
(
    rev      integer not null
        constraint revinfo_pkey
            primary key,
    revtstmp bigint
);

create table IF NOT EXISTS table_role
(
    role_id      bigserial not null
        constraint table_role_pkey
            primary key,
    created_by   varchar(255),
    created_date timestamp not null,
    modified_by  varchar(255),
    updated_date timestamp,
    version      bigint,
    role         varchar(255)
);

create table IF NOT EXISTS table_user
(
    user_id      bigserial not null
        constraint table_user_pkey
            primary key,
    created_by   varchar(255),
    created_date timestamp not null,
    modified_by  varchar(255),
    updated_date timestamp,
    version      bigint,
    active       boolean,
    email        varchar(255),
    fullname     varchar(255),
    password     varchar(255),
    username     varchar(255)
        constraint uk_username
            unique
);

create table IF NOT EXISTS user_role
(
    user_id bigint not null
        constraint fk_user_role_to_user
            references table_user,
    role_id bigint not null
        constraint fk_user_role_to_role
            references table_role
);


create table IF NOT EXISTS table_user_aud
(
    user_id      bigint  not null,
    rev          integer not null
        constraint fk_revinfotouseraud
            references revinfo,
    revtype      smallint,
    created_by   varchar(255),
    created_date timestamp,
    modified_by  varchar(255),
    updated_date timestamp,
    active       boolean,
    email        varchar(255),
    fullname     varchar(255),
    password     varchar(255),
    username     varchar(255),
    constraint table_user_aud_pkey
        primary key (user_id, rev)
);

create table IF NOT EXISTS user_role
(
    user_id bigint not null
        constraint fk_userrole_to_user
            references table_user,
    role_id bigint not null
        constraint fk_userrole_to_role
            references table_role
);

create table table_country
(
    id   bigserial    not null
        constraint table_country_pkey
            primary key,
    created_by   varchar(255),
    created_date timestamp not null,
    modified_by  varchar(255),
    updated_date timestamp,
    version      bigint,
    name         varchar(255),
    iso_code     varchar(255)
        constraint uk_isocode
            unique,
    population   bigint,
    region       varchar(255)
);
create table table_country_aud
(
    id bigint  not null,
    rev        integer not null
        constraint fk_revinfo
            references revinfo,
    revtype    smallint,
    name       varchar(255),
    iso_code   varchar(255),
    population bigint,
    region     varchar(255),
    constraint table_country_aud_pkey
        primary key (id, rev)
);

create table IF NOT EXISTS table_daily_stat
(
    id       bigserial    not null
        constraint table_daily_stat_pkey
            primary key,
    created_by       varchar(255),
    created_date     timestamp not null,
    modified_by      varchar(255),
    updated_date     timestamp,
    version      bigint,
    deaths       bigint,
    healing      bigint,
    new_infected bigint,
    testing      bigint,
    country      bigint
        constraint fk_daily_stat_to_country
            references table_country
);

create table IF NOT EXISTS table_daily_stat_aud
(
    id           bigint  not null,
    rev          integer not null
    constraint fk_daily_stat_to_revinfo
        references revinfo,
    revtype      smallint,
    deaths       bigint,
    healing      bigint,
    new_infected bigint,
    testing      bigint,
    country      bigint,
        constraint table_daily_stat_aud_pkey
            primary key (id, rev)
);

