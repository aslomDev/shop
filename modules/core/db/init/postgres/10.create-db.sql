-- begin SHOP_PRODUCT_CONTENT
create table SHOP_PRODUCT_CONTENT (
    ID integer,
    --
    productConentNameUz varchar(255) not null,
    productConentNameRu varchar(255) not null,
    fileProduct_id uuid,
    product_id integer,
    --
    primary key (ID)
)^
-- end SHOP_PRODUCT_CONTENT
-- begin SHOP_GOODS
create table SHOP_GOODS (
    ID integer,
    --
    goodsNameUz varchar(255) not null,
    goodsNameRu varchar(255) not null,
    --
    primary key (ID)
)^
-- end SHOP_GOODS
-- begin SHOP_PRODUCT
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
)^
-- end SHOP_PRODUCT
-- begin SHOP_CATEGORY
create table SHOP_CATEGORY (
    ID integer,
    --
    categoryNameUz varchar(255) not null,
    categoryNameRu varchar(255) not null,
    goods_id integer not null,
    --
    primary key (ID)
)^
-- end SHOP_CATEGORY
-- begin SHOP_USERS
create table SHOP_USERS (
    ID integer,
    --
    lastName varchar(255),
    firstName varchar(255),
    userName varchar(255),
    phoneNumber varchar(255),
    userId varchar(255),
    --
    primary key (ID)
)^
-- end SHOP_USERS