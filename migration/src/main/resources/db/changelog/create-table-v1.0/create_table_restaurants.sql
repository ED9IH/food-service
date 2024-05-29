create table restaurants
(
    id          bigint generated always as identity
        constraint restaurants_pk
            primary key,
    address     varchar not null,
    status      varchar not null,
    name        varchar not null,
    coordinates varchar not null
);

comment on table restaurants is 'Список ресторанов';

comment on column restaurants.id is 'Идентификатор ресторана';

comment on column restaurants.address is 'Адрес ресторана';

comment on column restaurants.status is 'Статус ресторана';

comment on column restaurants.name is 'Название ресторана';

comment on column restaurants.coordinates is 'Координаты ресторана';
