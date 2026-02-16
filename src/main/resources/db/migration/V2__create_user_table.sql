create table pfi_user (
                          id         bigserial primary key,
                          ds_name    varchar(255) not null,
                          ds_email   varchar(255) not null unique,
                          ds_password varchar(255) not null,
                          dt_created_at timestamp(6) not null
);