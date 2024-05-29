create table order_items
(
    id                    bigint generated always as identity
        constraint order_items_pk
            primary key,
    order_id              bigint
        constraint order_items_orders_id_fk
            references orders,
    restaurant_menu_items bigint
        constraint order_items_restaurant_menu_items_id_fk
            references restaurant_menu_items,
    price                 bigint  not null,
    quantity              integer not null
);

comment on table order_items is 'Список блюд в заказе';

comment on column order_items.id is 'Идентификатор блюда в заказе';

comment on column order_items.order_id is 'Идентификатор заказа';

comment on column order_items.restaurant_menu_items is 'Идентификатор блюда в меню';

comment on column order_items.price is 'Цена';

comment on column order_items.quantity is 'Количество блюд';
