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


INSERT INTO foodservice.public.restaurants(address, status, name, coordinates)
VALUES ('Kostina 13', 'OPEN', 'Shaurma', '56.3090659053749,43.99091476256555'),
       ('Kuznechikhinskaya st.80B', 'OPEN', 'Serbian Grill', '56.3090659053749,43.99091476256555');

INSERT INTO foodservice.public.couriers(phone,status,coordinates)
values ('+7777777777','COURIER_AVAILABLE','56.239928,43.958331');

INSERT INTO foodservice.public.customers(phone, email, address, coordinates)
VALUES ('+7888888888','svsto@mail.ru','Gogolya 34','56.322025,43.985693');

INSERT INTO foodservice.public.restaurant_menu_items(restaurant_id, name, price, image, description)
VALUES ('1', 'shaurma', 250, 'image', 'chiken'),
       ('2', 'Pleskavica classic', 320, 'image', 'Beef and pork burger');

