create table restaurant_menu_items
(
    id            bigint generated always as identity
        constraint restaurant_menu_items_pk
            primary key,
    restaurant_id bigint  not null
        constraint "FK_restaurant_menu_items_restaurants"
            references restaurants,
    name          varchar not null,
    price         bigint  not null,
    image         varchar not null,
    description   varchar not null
);

comment on table restaurant_menu_items is 'Меню ресторана';

comment on column restaurant_menu_items.id is 'Идентификатор позиции';

comment on column restaurant_menu_items.restaurant_id is 'Идентификатор ресторана';

comment on column restaurant_menu_items.name is 'Название блюда';

comment on column restaurant_menu_items.price is 'Цена блюда';

comment on column restaurant_menu_items.image is 'Изображение блюда';

comment on column restaurant_menu_items.description is 'Описание блюда';