-- update SHOP_PRODUCT_CONTENT set FILEPRODUCT_ID = <default_value> where FILEPRODUCT_ID is null ;
alter table SHOP_PRODUCT_CONTENT alter column FILEPRODUCT_ID set not null ;
-- update SHOP_PRODUCT_CONTENT set PRODUCT_ID = <default_value> where PRODUCT_ID is null ;
alter table SHOP_PRODUCT_CONTENT alter column PRODUCT_ID set not null ;
