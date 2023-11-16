DROP table IF EXISTS address cascade;
DROP table IF EXISTS bank_card cascade;
DROP table IF EXISTS users cascade;
DROP table IF EXISTS user_addresses cascade;
DROP table IF EXISTS delivery cascade;
DROP table IF EXISTS orders cascade;
DROP table IF EXISTS payment cascade;
DROP table IF EXISTS product_in_order cascade;
DROP table IF EXISTS product cascade;
DROP table IF EXISTS images_array cascade;
DROP table IF EXISTS product_category cascade;
DROP table IF EXISTS categories cascade;
DROP table IF EXISTS products_attributes cascade;
DROP table IF EXISTS attribute_type cascade;

CREATE TABLE IF NOT EXISTS product
(
    product_id BIGINT NOT NULL,
    vendor_code VARCHAR(30) NOT NULL,
    name VARCHAR(100) NOT NULL,
    attribute_arr_id BIGINT NOT NULL,
    CONSTRAINT product_id PRIMARY KEY (product_id),
    CONSTRAINT array_id FOREIGN KEY (attribute_arr_id) REFERENCES products_attributes (array_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS attribute_type
(
    attribute_type_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(20) NOT NULL,
    CONSTRAINT attribute_type_id PRIMARY KEY (attribute_type_id)
);

CREATE TABLE IF NOT EXISTS products_attributes
(
    products_attributes_id BIGINT NOT NULL,
    array_id BIGINT NOT NULL,
    attribute_type_id BIGINT NOT NULL,
    value_text VARCHAR(255) NOT NULL,
    value_integer INTEGER NOT NULL,
    value_float FLOAT NOT NULL,
    value_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    value_blob BYTEA NOT NULL,
    CONSTRAINT products_attributes_id PRIMARY KEY (products_attributes_id),
    CONSTRAINT attribute_arr_id FOREIGN KEY (array_id) REFERENCES product (attribute_arr_id) ON DELETE CASCADE,
    CONSTRAINT attribute_type_id FOREIGN KEY (attribute_type_id) REFERENCES attribute_type (attribute_type_id) ON DELETE CASCADE
);

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
   CONSTRAINT fk_orders_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
   CONSTRAINT fk_orders_product FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE
   /*CONSTRAINTS fk_orders_sales FOREIGN KEY (sales_id) REFERENCES sales (sales_id) ON DELETE CASCADE*/
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
    CONSTRAINT fk_product_in_order_product FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE,
    CONSTRAINT fk_product_in_order_orders FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS categories
(
    category_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    parent_id BIGINT NOT NULL,
    CONSTRAINT category_id PRIMARY KEY (category_id)
);

CREATE TABLE IF NOT EXISTS product_category
(
    product_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (product_id, category_id),
    CONSTRAINT product_id FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE,
    CONSTRAINT category_id FOREIGN KEY (category_id) REFERENCES categories (category_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS images_array
(
    image_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    CONSTRAINT product_id FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE
);