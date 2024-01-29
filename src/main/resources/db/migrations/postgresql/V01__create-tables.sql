CREATE TABLE IF NOT EXISTS cat_category (
    deleted          BOOLEAN      NOT NULL,
    creation_date    TIMESTAMP(6) NOT NULL,
    id               BIGSERIAL    PRIMARY KEY,
    last_update_date TIMESTAMP(6) NOT NULL,
    name             VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS ord_order (
    id               BIGSERIAL PRIMARY KEY,
    deleted          BOOLEAN NOT NULL,
    total_price      DOUBLE PRECISION NOT NULL,
    ord_client       VARCHAR(255) NOT NULL,
    creation_date    TIMESTAMP(6) NOT NULL,
    last_update_date TIMESTAMP(6) NOT NULL
);

CREATE TABLE IF NOT EXISTS prd_product (
    deleted          BOOLEAN          NOT NULL,
    price            DOUBLE PRECISION NOT NULL,
    category         BIGINT           NOT NULL
       CONSTRAINT fk_product_category
           REFERENCES cat_category,
    creation_date    TIMESTAMP(6)     NOT NULL,
    id               BIGSERIAL        PRIMARY KEY,
    last_update_date TIMESTAMP(6)     NOT NULL,
    name             VARCHAR(255)     NOT NULL
);

CREATE TABLE IF NOT EXISTS ori_order_item (
    deleted          BOOLEAN          NOT NULL,
    price            DOUBLE PRECISION NOT NULL,
    creation_date    TIMESTAMP(6)     NOT NULL,
    id               BIGSERIAL        PRIMARY KEY,
    last_update_date TIMESTAMP(6)     NOT NULL,
    ori_order        BIGINT           NOT NULL
      CONSTRAINT fk_order_item_order
          REFERENCES ord_order,
    product          BIGINT           NOT NULL
      CONSTRAINT fk_order_item_product
          REFERENCES prd_product,
    quantity         BIGINT           NOT NULL
);