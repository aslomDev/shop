package com.company.shop.web.screens.users;

import com.haulmont.cuba.gui.screen.*;
import com.company.shop.entity.Users;

@UiController("shop_Users.edit")
@UiDescriptor("users-edit.xml")
@EditedEntityContainer("usersDc")
@LoadDataBeforeShow
public class UsersEdit extends StandardEditor<Users> {
}