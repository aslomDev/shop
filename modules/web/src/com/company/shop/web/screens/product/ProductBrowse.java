package com.company.shop.web.screens.product;

import com.company.shop.entity.ProductContent;
import com.company.shop.entity.Users;
import com.company.shop.service.ProductService;
import com.company.shop.service.UserService;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.shop.entity.Product;

import javax.inject.Inject;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

@UiController("shop_Product.browse")
@UiDescriptor("product-browse.xml")
@LookupComponent("productsTable")
@LoadDataBeforeShow
public class ProductBrowse extends StandardLookup<Product> {


    @Inject
    private ProductService productService;
    @Inject
    private CollectionContainer<Product> productsDc;
    @Inject
    private CollectionLoader<Product> productsDl;
    @Inject
    private CollectionLoader<ProductContent> productContentLoader;
    @Inject
    private CollectionContainer<ProductContent> productContentCollection;
    @Inject
    private UserService userService;


    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        productContentLoader.setParameter("contentId", null);
        productContentLoader.load();
    }


    @Subscribe("sendBtn")
    public void onSendBtnClick(Button.ClickEvent event) {

        productContentLoader.setParameter("contentId", productsDl.getContainer().getItem().getId());
        productContentLoader.load();




        List<FileDescriptor> fileDescriptors = new LinkedList<>();
        for (ProductContent content : productContentCollection.getItems()){
            fileDescriptors.add(content.getFileProduct());
        }
        List<Users> usersList = userService.getAllUsers();
        usersList.forEach(i -> {
            productService.sendPhoto(i.getUserId(), productsDl.getContainer().getItem().getDescriptionUz(),  fileDescriptors);
        });

    }
}