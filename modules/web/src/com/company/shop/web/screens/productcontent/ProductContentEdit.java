package com.company.shop.web.screens.productcontent;

import com.haulmont.cuba.gui.screen.*;
import com.company.shop.entity.ProductContent;

@UiController("shop_ProductContent.edit")
@UiDescriptor("product-content-edit.xml")
@EditedEntityContainer("productContentDc")
@LoadDataBeforeShow
public class ProductContentEdit extends StandardEditor<ProductContent> {
}