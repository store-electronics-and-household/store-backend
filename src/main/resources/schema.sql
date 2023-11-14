DROP table IF EXISTS address cascade;
DROP table IF EXISTS bank_card cascade;
DROP table IF EXISTS users cascade;
DROP table IF EXISTS user_addresses cascade;

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


