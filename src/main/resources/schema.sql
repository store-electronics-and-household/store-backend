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
DROP table IF EXISTS product_attributes cascade;
DROP table IF EXISTS attribute_type cascade;
DROP table IF EXISTS attributes cascade;
DROP table IF EXISTS user_favorites cascade;
DROP table IF EXISTS product_in_favorites cascade;
DROP table IF EXISTS income cascade;
DROP table IF EXISTS user_basket cascade;
DROP table IF EXISTS outcome cascade;
DROP table IF EXISTS stockroom cascade;
DROP table IF EXISTS sales cascade;
DROP table IF EXISTS promotions cascade;


CREATE TABLE IF NOT EXISTS product
(
    product_id BIGINT NOT NULL,
    vendor_code VARCHAR(30) NOT NULL,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT product_id PRIMARY KEY (product_id)
);

CREATE TABLE IF NOT EXISTS attribute_type
(
    attribute_type_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(20) NOT NULL,
    CONSTRAINT attribute_type_id PRIMARY KEY (attribute_type_id)
);

CREATE TABLE IF NOT EXISTS attributes
(
    attribute_id BIGINT NOT NULL,
    attribute_type_id BIGINT NOT NULL REFERENCES attribute_type(attribute_type_id) ON DELETE CASCADE,
    value_text VARCHAR(255) NOT NULL,
    value_integer INTEGER NOT NULL,
    value_float FLOAT NOT NULL,
    value_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    value_blob BYTEA NOT NULL,
    CONSTRAINT products_attributes_id PRIMARY KEY (attribute_id)
);

CREATE TABLE IF NOT EXISTS product_attributes
(
    product_id BIGINT NOT NULL REFERENCES product (product_id) ON DELETE CASCADE,
    attribute_id BIGINT NOT NULL REFERENCES attributes (attribute_id) ON DELETE CASCADE,
    PRIMARY KEY (product_id, attribute_id)
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

CREATE TABLE IF NOT EXISTS sales
(
    sales_id             BIGINT       NOT NULL,
    name_sales           VARCHAR(100) NOT NULL,
    size_sales           VARCHAR(100) NOT NULL,
    product_id           BIGINT       NOT NULL,
    CONSTRAINT pk_sales_id PRIMARY KEY (sales_id),
    CONSTRAINT fk_user_basket_product FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS orders
(
   order_id BIGINT NOT NULL,
   user_id BIGINT NOT NULL,
   product_id BIGINT NOT NULL,
   created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   sum_total INTEGER NOT NULL,
   sales_id BIGINT NOT NULL,
   status VARCHAR(20) NOT NULL,
   CONSTRAINT pk_order_id PRIMARY KEY (order_id),
   CONSTRAINT fk_orders_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
   CONSTRAINT fk_orders_product FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE,
   CONSTRAINT fk_orders_sales FOREIGN KEY (sales_id) REFERENCES sales (sales_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS delivery
(
    delivery_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE,
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
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
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
    product_id BIGINT NOT NULL REFERENCES product(product_id) ON DELETE CASCADE,
    category_id BIGINT NOT NULL REFERENCES categories(category_id) ON DELETE CASCADE,
    PRIMARY KEY (product_id, category_id)
);

CREATE TABLE IF NOT EXISTS images_array
(
    image_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL REFERENCES product (product_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user_favorites
(
    user_favorites_id             BIGINT       NOT NULL,
    user_id                       BIGINT       NOT NULL,

    CONSTRAINT pk_user_favorites PRIMARY KEY (user_favorites_id),
    CONSTRAINT fk_users_favorites FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS product_in_favorites
(
    product_in_favorites_id             BIGINT       NOT NULL,
    product_id                          BIGINT       NOT NULL,
    favorites_id                        BIGINT       NOT NULL,

    CONSTRAINT pk_product_in_favorites PRIMARY KEY (product_in_favorites_id),
    CONSTRAINT fk_favorites_product FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE,
    CONSTRAINT fk_favorites FOREIGN KEY (favorites_id) REFERENCES user_favorites (user_favorites_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS income
(
    income_id             BIGINT       NOT NULL,
    product_id            BIGINT       NOT NULL,
    lot_number            VARCHAR(100) NOT NULL,
    income_price          VARCHAR(100) NOT NULL,
    count_income          VARCHAR(100) NOT NULL,
    sum_income            VARCHAR(1000) NOT NULL,
    income_date           TIMESTAMP WITHOUT TIME ZONE,

    CONSTRAINT pk_income PRIMARY KEY (income_id),
    CONSTRAINT fk_product_income FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS user_basket
(
    user_basket_id             BIGINT       NOT NULL,
    product_id                 BIGINT       NOT NULL,
    user_id                    BIGINT       NOT NULL,

    CONSTRAINT pk_user_basket PRIMARY KEY (user_basket_id),
    CONSTRAINT fk_users_basket_product FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE,
    CONSTRAINT fk_users_basket FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS outcome
(
    outcome_id             BIGINT       NOT NULL,
    order_id               BIGINT       NOT NULL,
    product_id             BIGINT       NOT NULL,
    price                  VARCHAR(100) NOT NULL,
    count_outcome          VARCHAR(100) NOT NULL,
    sale_id                BIGINT       NOT NULL,
    sum_outcome            VARCHAR(100) NOT NULL,

    CONSTRAINT pk_outcome PRIMARY KEY (outcome_id),
    CONSTRAINT fk_order_outcome FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
    CONSTRAINT fk_outcome_product FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE,
    CONSTRAINT fk_sale_outcome FOREIGN KEY (sale_id) REFERENCES sales (sales_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS stockroom
(
    stockroom_id             BIGINT       NOT NULL,
    product_id               BIGINT       NOT NULL,
    count_stockroom          VARCHAR(100) NOT NULL,
    reserve                  VARCHAR(100) NOT NULL,

    CONSTRAINT pk_stockroom PRIMARY KEY (stockroom_id),
    CONSTRAINT fk_stockroom_product FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE
    );



CREATE TABLE IF NOT EXISTS promotions
(
    promotions_id            BIGINT       NOT NULL,
    name_promotions          VARCHAR(100) NOT NULL,
    sale_id                  BIGINT       NOT NULL,
    product_id               BIGINT       NOT NULL,

    CONSTRAINT pk_promotions PRIMARY KEY (promotions_id),
    CONSTRAINT fk_sale_promotions FOREIGN KEY (sale_id) REFERENCES sales (sales_id) ON DELETE CASCADE,
    CONSTRAINT fk_product_promotions FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE
    );