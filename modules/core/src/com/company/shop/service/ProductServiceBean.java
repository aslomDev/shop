package com.company.shop.service;

import com.company.shop.config.TgConfig;
import com.company.shop.entity.Product;
import com.company.shop.entity.ProductContent;
import com.company.shop.entity.Users;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.FileLoader;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.security.app.Authentication;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

import javax.inject.Inject;
import java.awt.print.Pageable;
import java.util.LinkedList;
import java.util.List;

@Service(ProductService.NAME)
public class ProductServiceBean implements ProductService {
    @Inject private Persistence persistence;
    @Inject private UserService userService;
    @Inject private FileLoader fileLoader;
    @Inject Authentication authentication;
    @Inject private TgConfig tgConfig;
    @Inject private ActiveUserService activeUserService;

    @Override
    public List<Product> getProduct(Integer id) {
            List<Product> products;
            Transaction tx = persistence.createTransaction();
            try {
                TypedQuery<Product> query = persistence.getEntityManager()
                        .createQuery("select e from shop_Product e where e.category.id = :id", Product.class)
                        .addViewName("product-view");
                query.setParameter("id", id);
                products = query.getResultList();
                tx.commit();
            } finally {
                tx.end();
            }

            return products;
    }

    @Override
    public List<Product> getProductOffset(Integer id, Integer offset) {
        List<Product> products;
        Transaction tx = persistence.createTransaction();
        try {
            TypedQuery<Product> query = persistence.getEntityManager()
                    .createQuery("select e from shop_Product e where e.category.id = :id", Product.class)
                    .addViewName("product-view");
            query.setParameter("id", id);
            query.setFirstResult(offset);
            query.setMaxResults(1);
            products = query.getResultList();
            tx.commit();
        } finally {
            tx.end();
        }

        return products;
    }

    @Override
    public List<Product> getProductOut(Integer id, Integer offset, Integer size) {
        List<Product> products;
        Transaction tx = persistence.createTransaction();
        try {
            TypedQuery<Product> query = persistence.getEntityManager()
                    .createQuery("select e from shop_Product e where e.category.id = :id", Product.class)
                    .addViewName("product-view");
            query.setParameter("id", id);
            query.setFirstResult(offset);
            query.setMaxResults(size);
            products = query.getResultList();
            tx.commit();
        } finally {
            tx.end();
        }

        return products;
    }



    @Override
    public void sendPhoto(String id, String caption, List<FileDescriptor> file) {
        Transaction tx = persistence.createTransaction();
        try {
            if (userService.getUserId(id) != null){
                if (file.size() == 1){
                    String emoj = EmojiParser.parseToUnicode(caption);
                    SendPhoto sendPhoto = new SendPhoto();
                    sendPhoto.setChatId(id);
                    sendPhoto.setCaption(emoj);
                    InputFile inputFile = new InputFile();
                    inputFile.setMedia(fileLoader.openStream(file.get(0)), file.get(0).getName());
                    authentication.begin();
                    sendPhoto.setPhoto(inputFile);
                    tgConfig.sendPhotoUi(sendPhoto);
                    authentication.end();
                }else {
                    SendMediaGroup sendMediaGroup = new SendMediaGroup();
                    List<InputMedia> mediaList = new LinkedList<>();
                    int item = 0;
                    for (FileDescriptor fileDescriptor : file){
                        item=item + 1;
                        InputMediaPhoto inputMedia = new InputMediaPhoto();
                        inputMedia.setMedia(fileLoader.openStream(fileDescriptor), fileDescriptor.getName());
                        if (file.size() == item) {
                            String emoj = EmojiParser.parseToUnicode(caption);
                            inputMedia.setCaption(emoj);
                        }
                        mediaList.add(inputMedia);
                    }
                    sendMediaGroup.setChatId(id);
                    sendMediaGroup.setMedias(mediaList);
                    tgConfig.sendMediaUi(sendMediaGroup);
                }
                tx.commit();

            } else {
                System.out.println("not found user");
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            tx.end();
        }
    }

//    public void confirmUser()

}