DROP TABLE IF EXISTS products,discount_card,basket;

CREATE TABLE IF NOT EXISTS products
(
    id_product       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name_product     VARCHAR(100) UNIQUE,
    price_product    FLOAT4,
    discount_product boolean,
    CONSTRAINT product_pkey PRIMARY KEY (id_product)
);
CREATE TABLE IF NOT EXISTS discount_card
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    number_card      BIGINT,
    discount_percent INT,
    CONSTRAINT discount_card_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS basket
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name_product          VARCHAR(100) UNIQUE,
    price_product         FLOAT4  DEFAULT 0,
    quantity_product      FLOAT4  DEFAULT 0,
    discount_product      boolean DEFAULT FALSE,
    total_price           FLOAT4  DEFAULT 0,
    discount_card_percent FLOAT4  DEFAULT 0,
    total_discount        FLOAT4  DEFAULT 0,
    CONSTRAINT basket_pkey PRIMARY KEY (id)
);
