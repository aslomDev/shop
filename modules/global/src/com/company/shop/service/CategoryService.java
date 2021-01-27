package com.company.shop.service;

import com.company.shop.entity.Category;

import java.util.List;

public interface CategoryService {
    String NAME = "shop_CategoryService";

    List<Category> getCategory(Integer id);
}