package com.company.shop.web.screens.goods;

import com.haulmont.cuba.gui.screen.*;
import com.company.shop.entity.Goods;

@UiController("shop_Goods.browse")
@UiDescriptor("goods-browse.xml")
@LookupComponent("goodsesTable")
@LoadDataBeforeShow
public class GoodsBrowse extends StandardLookup<Goods> {
}