package com.company.shop.web.screens.goods;

import com.haulmont.cuba.gui.screen.*;
import com.company.shop.entity.Goods;

@UiController("shop_Goods.edit")
@UiDescriptor("goods-edit.xml")
@EditedEntityContainer("goodsDc")
@LoadDataBeforeShow
public class GoodsEdit extends StandardEditor<Goods> {
}