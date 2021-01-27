package com.company.shop.config;


import com.company.shop.config.utils.CommandUtils;
import com.company.shop.config.utils.MediaUtils;
import com.company.shop.config.utils.ProductContentUtils;
import com.company.shop.entity.ProductContent;
import com.haulmont.cuba.security.app.Authentication;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.inject.Inject;


@Component
public class TgConfig extends TelegramLongPollingBot {
    @Inject private CommandUtils commandUtils;
    @Inject private MediaUtils mediaUtils;
    @Inject private ProductContentUtils contentUtils;
    @Inject private Authentication authentication;
    @Inject private LangConfig langConfig;

    @Override
    public void onUpdateReceived(Update update) {
        authentication.begin();
       if (update.hasMessage()){
           sendMsg(commandUtils.message(update.getMessage().getText(), update.getMessage().getChatId().toString(), update));
       }else if (update.hasCallbackQuery()){
           String data = update.getCallbackQuery().getData();
           if (data.equals("start")){
               editMsg(commandUtils.start(update));
           }else if (data.equals("uz") || data.equals("ru")){
               langConfig.setLang(data);
               editMsg(commandUtils.goods(update));
           }else if (data.startsWith("goods#")){
               editMarkup(commandUtils.category(update));
           }else if (data.startsWith("category#")){
//               deleteMsg(commandUtils.deleteMessage(update));
               editMarkup(commandUtils.product(update));
           }else if (data.startsWith("product#")){
               deleteMsg(commandUtils.deleteMessage(update));
//               if (data.startsWith("product#delete")){
//                   deleteMsg(commandUtils.deleteMessage(update));
//               }
               sendMedia(mediaUtils.productContent(update));

               sendMsg(commandUtils.productBackButton(update));

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

    public void sendPhoto(SendPhoto sendPhoto) {
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
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
