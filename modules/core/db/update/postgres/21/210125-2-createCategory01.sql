alter table SHOP_CATEGORY add constraint FK_SHOP_CATEGORY_ON_TEST foreign key (TEST_ID) references SHOP_GOODS(ID) on delete CASCADE;
create unique index IDX_SHOP_CATEGORY_UK_categoryNameUz on SHOP_CATEGORY (categoryNameUz);
create unique index IDX_SHOP_CATEGORY_UK_categoryNameRu on SHOP_CATEGORY (categoryNameRu);
create index IDX_SHOP_CATEGORY_ON_TEST on SHOP_CATEGORY (TEST_ID);
