package com.company.shop.web.screens.activeuser;

import com.haulmont.cuba.gui.screen.*;
import com.company.shop.entity.ActiveUser;

@UiController("shop_ActiveUser.edit")
@UiDescriptor("active-user-edit.xml")
@EditedEntityContainer("activeUserDc")
@LoadDataBeforeShow
public class ActiveUserEdit extends StandardEditor<ActiveUser> {
}