create table public.couriers
(
    id          bigint generated always as identity
        constraint couriers_pk
            primary key,
    phone       bigint  not null,
    status      varchar not null,
    coordinates varchar not null
);
comment on table couriers is 'Список курьеров';
comment on column couriers.id is 'Идентификатор курьера';
comment on column couriers.phone is 'Телефон курьера';
comment on column couriers.status is 'Статус курьера';
comment on column couriers.coordinates is 'Координаты курьера';