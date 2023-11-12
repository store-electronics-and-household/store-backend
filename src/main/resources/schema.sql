CREATE TABLE IF NOT EXISTS address
(
    address_id      BIGINT        NOT NULL,
    town            VARCHAR(100)  NOT NULL,
    street          VARCHAR(100)  NOT NULL,
    house_number    VARCHAR(100)  NOT NULL,
    flat_number     VARCHAR(100)  NOT NULL,
    entrance_number VARCHAR(100)  NOT NULL,
    floor_number    VARCHAR(100)  NOT NULL,
    comment         VARCHAR(1000) NOT NULL,
    CONSTRAINT pk_address PRIMARY KEY (address_id)
);

CREATE TABLE IF NOT EXISTS bank_card
(
    bank_card_id   BIGINT       NOT NULL,
    name_oner      VARCHAR(100) NOT NULL,
    last_name_oner VARCHAR(100) NOT NULL,
    action_period  DATE         NOT NULL,
    CONSTRAINT pk_bank_card PRIMARY KEY (bank_card_id)
);

CREATE TABLE IF NOT EXISTS users
(
    user_id             BIGINT       NOT NULL,
    name                VARCHAR(100) NOT NULL,
    last_name           VARCHAR(100) NOT NULL,
    telephone_number    BIGINT       NOT NULL,
    address_id          BIGINT       NOT NULL,
    login               VARCHAR(100) NOT NULL,
    password            VARCHAR(100) NOT NULL,
    registration_status BOOLEAN      NOT NULL,
    bank_card_id        BIGINT       NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id),
    CONSTRAINT fk_users_address FOREIGN KEY (address_id) REFERENCES address (address_id),
    CONSTRAINT fk_users_bank_card FOREIGN KEY (bank_card_id) REFERENCES bank_card (bank_card_id)
);



