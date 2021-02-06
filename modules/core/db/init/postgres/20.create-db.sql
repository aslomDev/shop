-- begin SHOP_PRODUCT_CONTENT
alter table SHOP_PRODUCT_CONTENT add constraint FK_SHOP_PRODUCT_CONTENT_ON_FILEPRODUCT foreign key (FILEPRODUCT_ID) references SYS_FILE(ID)^
alter table SHOP_PRODUCT_CONTENT add constraint FK_SHOP_PRODUCT_CONTENT_ON_PRODUCT foreign key (PRODUCT_ID) references SHOP_PRODUCT(ID)^
create index IDX_SHOP_PRODUCT_CONTENT_ON_FILEPRODUCT on SHOP_PRODUCT_CONTENT (FILEPRODUCT_ID)^
create index IDX_SHOP_PRODUCT_CONTENT_ON_PRODUCT on SHOP_PRODUCT_CONTENT (PRODUCT_ID)^
-- end SHOP_PRODUCT_CONTENT
-- begin SHOP_GOODS
create unique index IDX_SHOP_GOODS_UK_goodsNameRu on SHOP_GOODS (goodsNameRu)^
create unique index IDX_SHOP_GOODS_UK_goodsNameUz on SHOP_GOODS (goodsNameUz)^
-- end SHOP_GOODS
-- begin SHOP_PRODUCT
alter table SHOP_PRODUCT add constraint FK_SHOP_PRODUCT_ON_CATEGORY foreign key (CATEGORY_ID) references SHOP_CATEGORY(ID) on delete CASCADE^
create index IDX_SHOP_PRODUCT_ON_CATEGORY on SHOP_PRODUCT (CATEGORY_ID)^
-- end SHOP_PRODUCT
-- begin SHOP_CATEGORY
alter table SHOP_CATEGORY add constraint FK_SHOP_CATEGORY_ON_GOODS foreign key (GOODS_ID) references SHOP_GOODS(ID) on delete CASCADE^
create unique index IDX_SHOP_CATEGORY_UK_categoryNameUz on SHOP_CATEGORY (categoryNameUz)^
create unique index IDX_SHOP_CATEGORY_UK_categoryNameRu on SHOP_CATEGORY (categoryNameRu)^
create index IDX_SHOP_CATEGORY_ON_GOODS on SHOP_CATEGORY (GOODS_ID)^
-- end SHOP_CATEGORY
-- begin SHOP_ACTIVE_USER
create unique index IDX_SHOP_ACTIVE_USER_UK_userId on SHOP_ACTIVE_USER (userId)^
-- end SHOP_ACTIVE_USER
-- begin SHOP_USERS
create unique index IDX_SHOP_USERS_UK_userId on SHOP_USERS (userId)^
-- end SHOP_USERS
