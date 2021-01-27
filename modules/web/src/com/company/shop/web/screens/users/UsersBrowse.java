package com.company.shop.web.screens.users;

import com.haulmont.cuba.gui.screen.*;
import com.company.shop.entity.Users;

@UiController("shop_Users.browse")
@UiDescriptor("users-browse.xml")
@LookupComponent("usersesTable")
@LoadDataBeforeShow
public class UsersBrowse extends StandardLookup<Users> {
}