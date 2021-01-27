package com.company.shop.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseIntegerIdEntity;
import com.haulmont.cuba.core.entity.HasUuid;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@NamePattern("%s|goodsUz")
@Table(name = "SHOP_GOODS")
@Entity(name = "shop_Goods")
public class Goods extends BaseIntegerIdEntity {
    private static final long serialVersionUID = 4491980219845074271L;

    @Column(name = "goodsNameUz", nullable = false, unique = true)
    private String goodsUz;

    @Column(name = "goodsNameRu", nullable = false, unique = true)
    private String goodsRu;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getGoodsUz() {
        return goodsUz;
    }

    public void setGoodsUz(String goodsUz) {
        this.goodsUz = goodsUz;
    }

    public String getGoodsRu() {
        return goodsRu;
    }

    public void setGoodsRu(String goodsRu) {
        this.goodsRu = goodsRu;
    }
}