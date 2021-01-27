package com.company.shop.web.screens.productcontent;

import com.haulmont.cuba.gui.screen.*;
import com.company.shop.entity.ProductContent;

@UiController("shop_ProductContent.browse")
@UiDescriptor("product-content-browse.xml")
@LookupComponent("productContentsTable")
@LoadDataBeforeShow
public class ProductContentBrowse extends StandardLookup<ProductContent> {
}