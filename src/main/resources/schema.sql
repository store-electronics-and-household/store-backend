DROP table IF EXISTS address cascade;
DROP table IF EXISTS bank_card cascade;
DROP table IF EXISTS users cascade;
DROP table IF EXISTS user_addresses cascade;
DROP table IF EXISTS delivery cascade;
DROP table IF EXISTS orders cascade;
DROP table IF EXISTS payment cascade;
DROP table IF EXISTS product_in_order cascade;

CREATE TABLE IF NOT EXISTS address
(
    id              BIGINT        NOT NULL,
    town            VARCHAR(100)  NOT NULL,
    street          VARCHAR(100)  NOT NULL,
    house_number    VARCHAR(100)  NOT NULL,
    flat_number     VARCHAR(100)  NOT NULL,
    entrance_number VARCHAR(100)  NOT NULL,
    floor_number    VARCHAR(100)  NOT NULL,
    comment         VARCHAR(1000) NOT NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS bank_card
(
    id             BIGINT       NOT NULL,
    name_oner      VARCHAR(100) NOT NULL,
    last_name_oner VARCHAR(100) NOT NULL,
    action_period  DATE         NOT NULL,
    CONSTRAINT pk_bank_card PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users
(
    id           BIGINT       NOT NULL,
    user_name    VARCHAR(100) NOT NULL,
    first_name   VARCHAR(100) NOT NULL,
    last_name    VARCHAR(100) NOT NULL,
    email        VARCHAR(100) NOT NULL,
    password     VARCHAR(100) NOT NULL,
    phone        VARCHAR(20)  NOT NULL,
    user_status  BIGINT       NOT NULL,
    address_id   BIGINT       NOT NULL,
    bank_card_id BIGINT       NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT fk_users_address FOREIGN KEY (address_id) REFERENCES address (id),
    CONSTRAINT fk_users_bank_card FOREIGN KEY (bank_card_id) REFERENCES bank_card (id)
);

CREATE TABLE IF NOT EXISTS user_addresses
(
    user_id    BIGINT NOT NULL,
    address_id BIGINT NOT NULL,
    CONSTRAINT pk_user_addresses PRIMARY KEY (user_id, address_id),
    CONSTRAINT fk_user_addresses_address FOREIGN KEY (address_id) REFERENCES address (id),
    CONSTRAINT fk_user_addresses_users FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS orders
(
   order_id BIGINT NOT NULL,
   user_id BIGINT NOT NULL,
   product_id BIGINT NOT NULL,
   date_created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   sum_total INTEGER NOT NULL,
   sales_id BIGINT NOT NULL,
   status VARCHAR(20) NOT NULL,
   CONSTRAINT pk_order_id PRIMARY KEY (order_id),
   CONSTRAINT fk_orders_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE/*,
   CONSTRAINT fk_orders_product FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE,
   CONSTRAINTS fk_orders_sales FOREIGN KEY (sales_id) REFERENCES sales (sales_id) ON DELETE CASCADE*/
);

CREATE TABLE IF NOT EXISTS delivery
(
    delivery_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    delivery_created TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_delivery_id PRIMARY KEY (delivery_id),
    CONSTRAINT fk_delivery_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_delivery_orders FOREIGN KEY (delivery_id) REFERENCES orders (order_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS payment
(
    payment_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    bank_card_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    payment_created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_payment_id PRIMARY KEY (payment_id),
    CONSTRAINT fk_payment_orders FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
    CONSTRAINT fk_payment_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_payment_bank_card FOREIGN KEY (bank_card_id) REFERENCES bank_card (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS product_in_order
(
    product_in_order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    CONSTRAINT pk_product_in_order PRIMARY KEY (product_in_order_id),
    /*CONSTRAINT fk_product_in_order_product FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE,*/
    CONSTRAINT fk_product_in_order_orders FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE
);