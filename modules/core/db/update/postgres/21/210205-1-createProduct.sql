create table SHOP_PRODUCT (
    ID integer,
    --
    productNameUz varchar(255) not null,
    productNameRu varchar(255) not null,
    descriptionUz text not null,
    descriptionRu text not null,
    category_id integer not null,
    --
    primary key (ID)
);