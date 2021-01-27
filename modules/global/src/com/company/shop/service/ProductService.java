package com.company.shop.service;

import com.company.shop.entity.Product;

import java.util.List;

public interface ProductService {
    String NAME = "shop_ProductService";

     List<Product> getProduct(Integer id);
}