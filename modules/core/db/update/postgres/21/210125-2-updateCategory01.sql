alter table SHOP_CATEGORY add constraint FK_SHOP_CATEGORY_ON_GOODS foreign key (GOODS_ID) references SHOP_GOODS(ID) on delete CASCADE;
create index IDX_SHOP_CATEGORY_ON_GOODS on SHOP_CATEGORY (GOODS_ID);
