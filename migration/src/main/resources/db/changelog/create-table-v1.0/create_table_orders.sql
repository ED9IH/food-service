create table orders
(
    id            bigint generated always as identity
        constraint orders_pk
            primary key,
    customer_id   bigint    not null
        constraint orders_customers_id_fk
            references customers,
    restaurant_id bigint    not null
        constraint orders_restaurants_id_fk
            references restaurants,
    status        varchar   not null,
    courier_id    bigint
        constraint orders_couriers_id_fk
            references couriers,
    timestamp     timestamp not null
);

comment on table orders is 'Список заказов';

comment on column orders.id is 'Идентификатор заказа';

comment on column orders.customer_id is 'Идентификатор покупателя';

comment on column orders.restaurant_id is 'Идентификатор ресторана';

comment on column orders.status is 'Статус заказа';

comment on column orders.courier_id is 'Идентификатор курьера';

comment on column orders.timestamp is 'Дата';
