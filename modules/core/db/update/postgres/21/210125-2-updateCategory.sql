alter table SHOP_CATEGORY rename column test_id to test_id__u64917 ;
alter table SHOP_CATEGORY alter column test_id__u64917 drop not null ;
alter table SHOP_CATEGORY drop constraint FK_SHOP_CATEGORY_ON_TEST ;
drop index IDX_SHOP_CATEGORY_ON_TEST ;
-- alter table SHOP_CATEGORY add column GOODS_ID integer ^
-- update SHOP_CATEGORY set GOODS_ID = <default_value> ;
-- alter table SHOP_CATEGORY alter column GOODS_ID set not null ;
alter table SHOP_CATEGORY add column GOODS_ID integer not null ;
