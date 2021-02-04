package com.company.shop.service;

import com.company.shop.entity.Product;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductService {
    String NAME = "shop_ProductService";

     List<Product> getProduct(Integer id);
     List<Product> getProductOffset(Integer id, Integer offset);
     List<Product> getProductOut(Integer id, Integer offset, Integer size);
}