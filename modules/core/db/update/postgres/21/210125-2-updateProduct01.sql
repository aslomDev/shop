alter table SHOP_PRODUCT add constraint FK_SHOP_PRODUCT_ON_CATEGORY foreign key (CATEGORY_ID) references SHOP_CATEGORY(ID) on delete CASCADE;
