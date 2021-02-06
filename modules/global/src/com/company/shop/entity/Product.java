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
import javax.validation.constraints.Size;
import java.util.UUID;
@NamePattern("%s|productUz")
@Table(name = "SHOP_PRODUCT")
@Entity(name = "shop_Product")
public class Product extends BaseIntegerIdEntity {
    private static final long serialVersionUID = 4004944911919928978L;

    @Column(name = "productNameUz", nullable = false)
    private String productUz;

    @Column(name = "productNameRu", nullable = false)
    private String productRu;

    @Lob()
    @Column(name = "descriptionUz", nullable = false)
    private String descriptionUz;

    @Lob()
    @Column(name = "descriptionRu", nullable = false)
    private String descriptionRu;


    @Lookup(type = LookupType.DROPDOWN, actions = "lookup")
    @NotNull
    @OnDeleteInverse(DeletePolicy.CASCADE)
    private @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Category category;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProductUz() {
        return productUz;
    }

    public void setProductUz(String productUz) {
        this.productUz = productUz;
    }

    public String getProductRu() {
        return productRu;
    }

    public void setProductRu(String productRu) {
        this.productRu = productRu;
    }

    public String getDescriptionUz() {
        return descriptionUz;
    }

    public void setDescriptionUz(String descriptionUz) {
        this.descriptionUz = descriptionUz;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}