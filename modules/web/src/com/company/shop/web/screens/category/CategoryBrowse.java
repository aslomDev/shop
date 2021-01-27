package com.company.shop.web.screens.category;

import com.haulmont.cuba.gui.screen.*;
import com.company.shop.entity.Category;

@UiController("shop_Category.browse")
@UiDescriptor("category-browse.xml")
@LookupComponent("categoriesTable")
@LoadDataBeforeShow
public class CategoryBrowse extends StandardLookup<Category> {
}