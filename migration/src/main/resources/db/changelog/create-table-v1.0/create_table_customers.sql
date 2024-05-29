create table customers
(
    id          bigint generated always as identity
        constraint customers_pk
            primary key,
    phone       varchar      not null,
    email       varchar     not null,
    address     varchar     not null,
    coordinates varchar(32) not null
);

comment on table customers is 'Список клиентов';

comment on column customers.id is 'Идентификатор клиента';

comment on column customers.phone is 'Телефон клиента';

comment on column customers.email is 'Адрес электронной почты клиента';

comment on column customers.address is 'Адрес клиента';

comment on column customers.coordinates is 'Координаты клиента';
