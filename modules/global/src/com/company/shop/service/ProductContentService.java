package com.company.shop.service;

import com.company.shop.entity.ProductContent;

import java.util.List;

public interface ProductContentService {
    String NAME = "shop_ProductContentService";

    List<ProductContent> getProductContent(Integer id);
}