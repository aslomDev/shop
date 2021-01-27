package com.company.shop.web.screens.category;

import com.haulmont.cuba.gui.screen.*;
import com.company.shop.entity.Category;

@UiController("shop_Category.edit")
@UiDescriptor("category-edit.xml")
@EditedEntityContainer("categoryDc")
@LoadDataBeforeShow
public class CategoryEdit extends StandardEditor<Category> {
}