DROP table IF EXISTS addresses cascade;
DROP table IF EXISTS users cascade;
DROP TABLE IF EXISTS authorities CASCADE;
DROP table IF EXISTS user_addresses cascade;
DROP table IF EXISTS delivery cascade;
DROP table IF EXISTS orders cascade;
DROP table IF EXISTS payments cascade;
DROP table IF EXISTS products_in_order cascade;
DROP table IF EXISTS products cascade;
DROP table IF EXISTS images cascade;
DROP table IF EXISTS product_category cascade;
DROP table IF EXISTS categories cascade;
DROP table IF EXISTS product_attributes cascade;
DROP table IF EXISTS attribute_types cascade;
DROP table IF EXISTS attributes cascade;
DROP table IF EXISTS user_favorites cascade;
DROP table IF EXISTS product_in_favorites cascade;
DROP table IF EXISTS incomes cascade;
DROP table IF EXISTS users_basket cascade;
DROP table IF EXISTS basket_products cascade;
DROP table IF EXISTS outcomes cascade;
DROP table IF EXISTS stockrooms cascade;
DROP table IF EXISTS sales cascade;
DROP table IF EXISTS promotions cascade;

CREATE TABLE IF NOT EXISTS products
(
    product_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    vendor_code VARCHAR(30) NOT NULL,
    name VARCHAR(100) NOT NULL,
    category_id BIGINT NOT NULL
);

/*CREATE TABLE IF NOT EXISTS attribute_types
(
    attribute_type_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    attribute_name VARCHAR(100) NOT NULL,
    attribute_type VARCHAR(20) NOT NULL
);*/

CREATE TABLE IF NOT EXISTS attributes
(
    attribute_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    attribute_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS product_attributes
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    product_id BIGINT NOT NULL REFERENCES products (product_id) ON DELETE CASCADE,
    attribute_id BIGINT NOT NULL REFERENCES attributes (attribute_id) ON DELETE CASCADE,
    attribute_value VARCHAR(255) NOT NULL,
    CONSTRAINT fk_product_attributes_product FOREIGN KEY (product_id) REFERENCES products (product_id),
    CONSTRAINT fk_product_attributes_attributes FOREIGN KEY (attribute_id) REFERENCES attributes (attribute_id)
);

CREATE TABLE IF NOT EXISTS addresses
(
    address_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    town VARCHAR(100) NOT NULL,
    street VARCHAR(100) NOT NULL,
    house_number VARCHAR(100) NOT NULL,
    flat_number VARCHAR(100) NOT NULL,
    entrance_number VARCHAR(100) NOT NULL,
    floor_number VARCHAR(100) NOT NULL,
    comment VARCHAR(1000) NOT NULL
);


CREATE TABLE IF NOT EXISTS users (
                                     user_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                     username varchar(250) unique NOT NULL,
                                     email varchar(250) unique NOT NULL,
                                     password varchar(250) NOT NULL,
                                     enabled boolean default true NOT NULL,
                                     first_name varchar(250),
                                     last_name varchar(250),
                                     phone varchar(250),
                                     address_id BIGINT, --(NOT NULL?)
                                     CONSTRAINT fk_users_address FOREIGN KEY (address_id) REFERENCES addresses (address_id)
);

CREATE TABLE IF NOT EXISTS authorities (
                                           authority_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                           username varchar (250) references users (username),
                                           authority varchar (15)
);

CREATE TABLE IF NOT EXISTS user_favorites
(
    user_id BIGINT NOT NULL REFERENCES users (user_id) ON DELETE CASCADE,
    product_id BIGINT NOT NULL REFERENCES products (product_id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, product_id)
);

CREATE TABLE IF NOT EXISTS user_addresses
(
    user_id    BIGINT NOT NULL,
    address_id BIGINT NOT NULL,
    CONSTRAINT pk_user_addresses PRIMARY KEY (user_id, address_id),
    CONSTRAINT fk_user_addresses_address FOREIGN KEY (address_id) REFERENCES addresses (address_id),
    CONSTRAINT fk_user_addresses_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE IF NOT EXISTS promotions
(
    promotion_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    promotion_name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS sales
(
    sale_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    sale_name VARCHAR(100) NOT NULL,
    quantity VARCHAR(100) NOT NULL,
    product_id BIGINT NOT NULL,
    promotion_id BIGINT,
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products (product_id) ON DELETE CASCADE,
    CONSTRAINT fk_promotion_of_sale FOREIGN KEY (promotion_id) REFERENCES promotions (promotion_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS orders
(
    order_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    sum_total INTEGER NOT NULL,
    sale_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_orders_users FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    CONSTRAINT fk_orders_sales FOREIGN KEY (sale_id) REFERENCES sales (sale_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS delivery
(
    delivery_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT fk_delivery_users FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    CONSTRAINT fk_delivery_orders FOREIGN KEY (delivery_id) REFERENCES orders (order_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS payments
(
    payment_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    payment_operation_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_payment_orders FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
    CONSTRAINT fk_payment_users FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS products_in_order
(
    product_in_order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    CONSTRAINT pk_product_in_order PRIMARY KEY (product_in_order_id),
    CONSTRAINT fk_product_in_order_product FOREIGN KEY (product_id) REFERENCES products (product_id) ON DELETE CASCADE,
    CONSTRAINT fk_product_in_order_orders FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS categories
(
    category_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL,
    parent_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS product_category
(
    product_id BIGINT NOT NULL REFERENCES products (product_id) ON DELETE CASCADE,
    category_id BIGINT NOT NULL REFERENCES categories(category_id) ON DELETE CASCADE,
    PRIMARY KEY (product_id, category_id)
);

CREATE TABLE IF NOT EXISTS images
(
    image_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    product_id BIGINT NOT NULL REFERENCES products (product_id) ON DELETE CASCADE,
    image_link VARCHAR(1024) NOT NULL
);

CREATE TABLE IF NOT EXISTS incomes
(
    income_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    product_id BIGINT NOT NULL,
    lot_number VARCHAR(100) NOT NULL,
    price VARCHAR(100) NOT NULL,
    quantity VARCHAR(100) NOT NULL,
    income_sum VARCHAR(1000) NOT NULL,
    income_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT fk_product_income FOREIGN KEY (product_id) REFERENCES products (product_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS users_basket
(
    user_basket_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id  BIGINT NOT NULL,
    CONSTRAINT fk_users_basket FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS basket_products
(
    basket_id BIGINT NOT NULL REFERENCES users_basket (user_basket_id) ON DELETE CASCADE,
    product_id BIGINT NOT NULL REFERENCES products(product_id) ON DELETE CASCADE,
    PRIMARY KEY (basket_id, product_id)
);

CREATE TABLE IF NOT EXISTS outcomes
(
    outcome_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    price VARCHAR(100) NOT NULL,
    count_outcome VARCHAR(100) NOT NULL,
    sale_id BIGINT NOT NULL,
    outcome_sum VARCHAR(100) NOT NULL,
    CONSTRAINT fk_order_outcome FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
    CONSTRAINT fk_outcome_product FOREIGN KEY (product_id) REFERENCES products (product_id) ON DELETE CASCADE,
    CONSTRAINT fk_sale_outcome FOREIGN KEY (sale_id) REFERENCES sales (sale_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS stockrooms
(
    stockroom_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    product_id BIGINT NOT NULL,
    count_stockroom VARCHAR(100) NOT NULL,
    reserve VARCHAR(100) NOT NULL,
    CONSTRAINT fk_stockroom_product FOREIGN KEY (product_id) REFERENCES products (product_id) ON DELETE CASCADE
);

INSERT INTO products VALUES (1,'123','dolore',1);
INSERT INTO products VALUES (2,'1232','dolore2',2);
INSERT INTO products VALUES (3,'1233','dolore3',3);
INSERT INTO products VALUES (4,'1234','dolore4',1);
INSERT INTO products VALUES (5,'1235','dolore5',2);
INSERT INTO products VALUES (6,'1236','dolore6',2);
INSERT INTO products VALUES (7,'1237','dolore7',1);
INSERT INTO products VALUES (8,'1238','dolore8',4);

INSERT INTO categories VALUES (1,'categories',1);
INSERT INTO categories VALUES (2,'categories2',1);
INSERT INTO categories VALUES (3,'categories3',1);
INSERT INTO categories VALUES (4,'categories4',1);
INSERT INTO categories VALUES (5,'categories5',1);
INSERT INTO categories VALUES (6,'categories6',1);
INSERT INTO categories VALUES (7,'categories7',1);
INSERT INTO categories VALUES (8,'categories8',1);
INSERT INTO categories VALUES (9,'categories9',1);

INSERT INTO attributes VALUES (1,'aaaaa');
INSERT INTO attributes VALUES (2,'bbbbb');
INSERT INTO attributes VALUES (3,'ccccc');
INSERT INTO attributes VALUES (4,'dddd');
INSERT INTO attributes VALUES (5,'eeee');
INSERT INTO attributes VALUES (6,'fffff');
INSERT INTO attributes VALUES (7,'gggggg');
INSERT INTO attributes VALUES (8,'hhhhhhh');
INSERT INTO attributes VALUES (9,'uuuuuuu');

INSERT INTO product_attributes VALUES (1,1,1,'f1');
INSERT INTO product_attributes VALUES (2,1,3,'a2');
INSERT INTO product_attributes VALUES (3,1,5,'h3');
INSERT INTO product_attributes VALUES (4,1,8,'h4');
INSERT INTO product_attributes VALUES (5,2,1,'k5');
INSERT INTO product_attributes VALUES (6,2,2,'c6');
INSERT INTO product_attributes VALUES (7,3,3,'x7');
INSERT INTO product_attributes VALUES (8,4,1,'j8');
INSERT INTO product_attributes VALUES (9,5,4,'z9');
INSERT INTO product_attributes VALUES (10,5,3,'j10');
INSERT INTO product_attributes VALUES (11,5,8,'h11');
