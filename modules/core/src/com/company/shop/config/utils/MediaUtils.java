package com.company.shop.config.utils;

import com.company.shop.config.LangConfig;
import com.company.shop.config.SessionIdConfig;
import com.company.shop.entity.ProductContent;
import com.company.shop.service.ProductContentService;
import com.company.shop.service.ProductService;
import com.haulmont.cuba.core.global.FileLoader;
import com.haulmont.cuba.core.global.FileStorageException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

import javax.inject.Inject;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Component
public class MediaUtils {
    @Inject private ProductContentService productContentService;
    @Inject private FileLoader fileLoader;
    @Inject private ProductService productService;
    @Inject private SessionIdConfig sessionIdConfig;
    @Inject private LangConfig langConfig;

    public SendMediaGroup productContent(Update update){
        Message message = update.getCallbackQuery().getMessage();
        String data = update.getCallbackQuery().getData();
        String ol = data.substring(8);
        System.out.println("date to id " + ol);
        int i = Integer.parseInt(ol);
        SendMediaGroup sendMediaGroup = new SendMediaGroup();
        List<InputMedia> inputMediaList = new LinkedList<>();
        List<ProductContent> list = productContentService.getProductContent(i);
        int caption = 0;
        if (langConfig.getLang().equals("uz")){
            for (ProductContent content : list){
                try {
                    caption=caption + 1;
                    InputMediaPhoto inputMedia = new InputMediaPhoto();
                    inputMedia.setMedia(fileLoader.openStream(content.getFileProduct()), content.getFileProduct().getName());
                    if (list.size() == caption){
                        productService.getProduct(sessionIdConfig.getCategoryId()).forEach(dis -> {
                            inputMedia.setCaption(dis.getDescriptionUz());
                        });
                    }
                    inputMediaList.add(inputMedia);
                }catch (FileStorageException e){
                    e.printStackTrace();
                }
            }
        }else if (langConfig.getLang().equals("ru")){
            for (ProductContent content : list){
                try {
                    caption=caption + 1;
                    InputMediaPhoto inputMedia = new InputMediaPhoto();
                    inputMedia.setMedia(fileLoader.openStream(content.getFileProduct()), content.getFileProduct().getName());
                    if (list.size() == caption){
                        productService.getProduct(sessionIdConfig.getCategoryId()).forEach(dis -> {
                            inputMedia.setCaption(dis.getDescriptionRu());
                        });
                    }
                    inputMediaList.add(inputMedia);
                }catch (FileStorageException e){
                    e.printStackTrace();
                }
            }
        }

        System.out.println("inputList " + inputMediaList);

        sendMediaGroup.setChatId(message.getChatId().toString());
        sendMediaGroup.setMedias(inputMediaList);
        return sendMediaGroup;
    }

}
