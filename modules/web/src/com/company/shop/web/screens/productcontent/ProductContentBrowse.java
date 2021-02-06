package com.company.shop.web.screens.productcontent;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.FileDescriptorResource;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.Image;
import com.haulmont.cuba.gui.screen.*;
import com.company.shop.entity.ProductContent;

import javax.inject.Inject;

@UiController("shop_ProductContent.browse")
@UiDescriptor("product-content-browse.xml")
@LookupComponent("productContentsTable")
@LoadDataBeforeShow
public class ProductContentBrowse extends StandardLookup<ProductContent> {

    @Inject
    private UiComponents uiComponents;
    @Inject
    private GroupTable<ProductContent> productContentsTable;

    @Subscribe
    protected void onInit(InitEvent event) {
        productContentsTable.addGeneratedColumn(
                "file",
                this::renderAvatarImageComponent
        );
    }

    private Component renderAvatarImageComponent(ProductContent content) {
        FileDescriptor imageFile = content.getFileProduct();

        if (imageFile == null) {
            return null;
        }

        Image image = smallAvatarImage();
        image.setSource(FileDescriptorResource.class)
                .setFileDescriptor((FileDescriptor) imageFile);


        return image;
    }

    private Image smallAvatarImage() {
        Image image = uiComponents.create(Image.class);
        image.setScaleMode(Image.ScaleMode.CONTAIN);
        image.setHeight("100");
        image.setWidth("100");
        image.setStyleName("avatar-icon-small");
        return image;
    }

}