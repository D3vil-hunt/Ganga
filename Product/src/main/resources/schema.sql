CREATE TABLE product
(
    id             BIGINT,
    display_name   VARCHAR(20),
    short_description      VARCHAR(20),
    description    VARCHAR(100),
    category       VARCHAR(10),
    price          INTEGER,
    discount       INTEGER,
    delivery_charge INTEGER,
    offer_price     INTEGER,
    seller_count    INTEGER,
    avg_rating      FLOAT,
    review         BIGINT
);

CREATE TABLE review
(
    id BIGINT,
    product bigint,
    subject varchar(50),
    body varchar(200)
);

alter table product add primary key (id);
alter table review add primary key (id);

