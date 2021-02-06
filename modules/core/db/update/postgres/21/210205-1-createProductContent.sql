create table SHOP_PRODUCT_CONTENT (
    ID integer,
    --
    productConentNameUz varchar(255) not null,
    productConentNameRu varchar(255) not null,
    fileProduct_id uuid,
    product_id integer not null,
    --
    primary key (ID)
);