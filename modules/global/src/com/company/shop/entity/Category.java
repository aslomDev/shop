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

@NamePattern("%s|categoryUz")
@Table(name = "SHOP_CATEGORY")
@Entity(name = "shop_Category")
public class Category extends BaseIntegerIdEntity{
    private static final long serialVersionUID = 1856454773176664534L;

    @Column(name = "categoryNameUz", nullable = false, unique = true)
    private String categoryUz;

    @Column(name = "categoryNameRu", nullable = false, unique = true)
    private String categoryRu;

    @Lookup(type = LookupType.DROPDOWN, actions = "lookup")
    @NotNull
    @OnDeleteInverse(DeletePolicy.CASCADE)
    private @JoinColumn(name = "goods_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Goods goods;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCategoryUz() {
        return categoryUz;
    }

    public void setCategoryUz(String categoryUz) {
        this.categoryUz = categoryUz;
    }

    public String getCategoryRu() {
        return categoryRu;
    }

    public void setCategoryRu(String categoryRu) {
        this.categoryRu = categoryRu;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}