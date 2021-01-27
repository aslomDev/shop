package com.company.shop.service;

import com.company.shop.entity.Goods;

import java.util.List;

public interface GoodsService {
    String NAME = "shop_GoodsService";

    List<Goods> getGoods();

}