create table public.couriers
(
    id          bigint generated always as identity
        constraint couriers_pk
            primary key,
    phone       bigint  not null,
    status      varchar not null,
    coordinates varchar not null
);

create table public.customers
(
    id      bigint generated always as identity
        constraint customers_pk
            primary key,
    phone   integer not null,
    email   varchar not null,
    address varchar not null
);

create table public.order_items
(
    id                    bigint  not null
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

create table public.orders
(
    id            bigint    not null
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
create table public.orders
(
    id            bigint    not null
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
create table public.restaurants
(
    id          bigint  not null
        constraint restaurants_pk
            primary key,
    address     varchar not null,
    status      varchar not null,
    name        varchar not null,
    coordinates varchar not null
);
