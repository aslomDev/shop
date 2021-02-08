alter table SHOP_PRODUCT_CONTENT rename column productconentnameuz to productconentnameuz__u36483 ;
alter table SHOP_PRODUCT_CONTENT alter column productconentnameuz__u36483 drop not null ;
alter table SHOP_PRODUCT_CONTENT add column PRODUCTCONENTNAME varchar(255) ^
update SHOP_PRODUCT_CONTENT set PRODUCTCONENTNAME = '' where PRODUCTCONENTNAME is null ;
alter table SHOP_PRODUCT_CONTENT alter column PRODUCTCONENTNAME set not null ;
