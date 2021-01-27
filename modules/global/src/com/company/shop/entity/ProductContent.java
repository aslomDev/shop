package com.company.shop.entity;

import com.haulmont.cuba.core.entity.BaseIntegerIdEntity;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.HasUuid;

import javax.persistence.*;
import java.util.UUID;


@Table(name = "SHOP_PRODUCT_CONTENT")
@Entity(name = "shop_ProductContent")
public class ProductContent extends BaseIntegerIdEntity {
    private static final long serialVersionUID = -5778583826035306996L;

    @Column(name = "productConentNameUz", nullable = false)
    private String productContentUz;

    @Column(name = "productConentNameRu", nullable = false)
    private String productContentRu;


    private @JoinColumn(name = "fileProduct_id")
    @ManyToOne(fetch = FetchType.LAZY)
    FileDescriptor fileProduct;

    private @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Product product;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProductContentUz() {
        return productContentUz;
    }

    public void setProductContentUz(String productContentUz) {
        this.productContentUz = productContentUz;
    }

    public String getProductContentRu() {
        return productContentRu;
    }

    public void setProductContentRu(String productContentRu) {
        this.productContentRu = productContentRu;
    }

    public FileDescriptor getFileProduct() {
        return fileProduct;
    }

    public void setFileProduct(FileDescriptor fileProduct) {
        this.fileProduct = fileProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}