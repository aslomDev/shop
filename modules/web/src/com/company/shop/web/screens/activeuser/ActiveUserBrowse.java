package com.company.shop.web.screens.activeuser;

import com.company.shop.service.ActiveUserService;
import com.company.shop.service.UserService;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.shop.entity.ActiveUser;

import javax.inject.Inject;

@UiController("shop_ActiveUser.browse")
@UiDescriptor("active-user-browse.xml")
@LookupComponent("activeUsersTable")
@LoadDataBeforeShow
public class ActiveUserBrowse extends StandardLookup<ActiveUser> {
    @Inject
    private UserService userService;
    @Inject
    private ActiveUserService activeUserService;
    @Inject
    private CollectionContainer<ActiveUser> activeUsersDc;

    @Subscribe("removeBtn")
    public void onRemoveBtnClick(Button.ClickEvent event) {
        String id = activeUsersDc.getItem().getUserId();
        userService.deleteNoActive(id);
        activeUserService.deleteNoActive(id);
    }
}