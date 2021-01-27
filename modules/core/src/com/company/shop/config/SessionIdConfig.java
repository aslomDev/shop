package com.company.shop.config;


import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.defaults.Default;
import com.haulmont.cuba.core.config.defaults.DefaultInt;

public interface SessionIdConfig extends Config {

    @Property("shop.categoryId")
    @DefaultInt(0)
    int getCategoryId();
    void setCategoryId(int id);

    @Property("shop.productId")
    @DefaultInt(0)
    int getProductId();
    void setProductId(int id);


    @Property("shop.goodsId")
    @DefaultInt(0)
    int getGoodsId();
    void setGoodsId(int id);

    @Property("shop.pContentId")
    @DefaultInt(0)
    int getPContentId();
    void setPContentId(int id);

    @Property("shop.id")
    @DefaultInt(0)
    int getShopId();
    void setShopId(int id);



}
