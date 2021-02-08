package com.company.shop.config;


import com.company.shop.config.utils.*;

import com.company.shop.entity.Users;
import com.company.shop.service.ActiveUserService;
import com.company.shop.service.UserService;
import com.haulmont.cuba.security.app.Authentication;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.inject.Inject;
import java.io.IOException;

@Component
public class TgConfig extends TelegramLongPollingBot {
    @Inject private CommandUtils commandUtils;
    @Inject private MediaUtils mediaUtils;
    @Inject private Authentication authentication;
    @Inject private Lang lang;
    @Inject private ActiveUserService activeUserService;
    @Inject private UserService userService;

    @Override
    public void onUpdateReceived(Update update) {
//        try {
//            mediaUtils.uploadFile(update.getMessage().getPhoto().get(2).getFileId());
//
////            System.out.println("file "+ update.getMessage().getPhoto().get(2).getFilePath() + " " + update.getMessage().getPhoto().get(2).getFileId());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        authentication.begin();
       if (update.hasMessage()){
           sendMsg(commandUtils.message(update.getMessage().getText(), update.getMessage().getChatId().toString(), update));
       }else if (update.hasCallbackQuery()){
           String data = update.getCallbackQuery().getData();
           if (data.equals("start")){
               editMsg(commandUtils.start(update));
           }else if (data.equals("uz") || data.equals("ru")){
               userService.lang(update.getCallbackQuery().getFrom().getId().toString(), data);
               editMsg(commandUtils.goods(update));
           }else if (data.startsWith("goods#")){
               editMarkup(commandUtils.category(update));
           }else if (data.startsWith("category#")){
               if (!mediaUtils.outSize(update) && mediaUtils.contentOutSize(update)){
                   deleteMsg(commandUtils.deleteMessage(update));
                   if (data.startsWith("category#next")){
                       mediaUtils.pContentNext(update);
                       mediaUtils.pContentLoopNext(update);
                       mediaUtils.outButtonProduct(update);
                   }else {
                       if (!mediaUtils.contetn1(update)) {
                           mediaUtils.pContentLoop(update);
                       }
                       mediaUtils.outButtonProduct(update);
                   }
                      if (!lang.getOneContent(update)){
                          sendMedia(mediaUtils.productAndContent(update));
                          sendMsg(commandUtils.productNextButton(update));
                      }else {
                          sendPhoto(mediaUtils.sendMediaPhoto(update));
                          sendMsg(commandUtils.productNextButton(update));
                      }
               }else {
                   editMarkup(commandUtils.contentIsNull(update));
               }
           }
       }
       authentication.end();

    }



    public void sendMsg(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void editMsg(EditMessageText editMessageText) {
        try {
            execute(editMessageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void editMarkup(EditMessageReplyMarkup editMarkup) {
        try {
            execute(editMarkup);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMedia(SendMediaGroup sendMediaGroup) {
        try {
            execute(sendMediaGroup);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMediaUi(SendMediaGroup sendMediaGroup) {
        try {
            execute(sendMediaGroup);
        } catch (TelegramApiException e) {
            Users users = userService.getUserId(sendMediaGroup.getChatId());
            activeUserService.createNoActive("этот пользователь умер", users.getFirstName(), users.getLastName(), users.getUserName(), users.getUserId());
            e.printStackTrace();
        }
    }

    public void sendPhoto(SendPhoto sendPhoto) {
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendPhotoUi(SendPhoto sendPhoto) {
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            Users users = userService.getUserId(sendPhoto.getChatId());
            activeUserService.createNoActive("этот пользователь умер", users.getFirstName(), users.getLastName(), users.getUserName(), users.getUserId());
            e.printStackTrace();
        }
    }

    public void deleteMsg(DeleteMessage deleteMessage) {
        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }














    @Override
    public String getBotUsername() {
        return "lolMessagBot";
    }

    @Override
    public String getBotToken() {
        return "1487031714:AAEz7teOXNw1CmUG9BCUxp225NcWYVZ8p5w";
    }
}
