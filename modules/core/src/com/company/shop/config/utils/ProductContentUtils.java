package com.company.shop.config.utils;

import com.company.shop.config.SessionIdConfig;
import com.company.shop.entity.ProductContent;
import com.company.shop.service.ProductService;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.FileLoader;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

import javax.inject.Inject;
import java.io.File;

@Component
public class ProductContentUtils {
    @Inject private ProductService productService;
    @Inject private SessionIdConfig sessionIdConfig;
    @Inject private MediaUtils mediaUtils;
    @Inject private FileLoader fileLoader;


    public SendPhoto sendProductContent(Update update){
        SendMessage sendMessage = new SendMessage();
        SendPhoto sendPhoto = new SendPhoto();
        Message message = update.getCallbackQuery().getMessage();

        InputMediaPhoto photo = new InputMediaPhoto();


        productService.getProduct(sessionIdConfig.getCategoryId()).forEach(i -> {
//            sendMessage.setText(i.getDescriptionUz());
//            sendMessage.setChatId(message.getChatId().toString());



//            mediaUtils.productContent(update).getMedias().get(1).getNewMediaFile(), mediaUtils.productContent(update).getMedias().get(1).getMediaName();
            InputFile inputFile = new InputFile();
            inputFile.setMedia(mediaUtils.productContent(update).getMedias().get(1).getNewMediaFile());
            sendPhoto.setPhoto(inputFile);
            sendPhoto.setChatId(message.getChatId().toString());
            sendPhoto.setCaption(i.getDescriptionRu());
        });

        return sendPhoto;



    }

}
