package com.company.shop.config.utils;

import com.company.shop.config.LangConfig;
import com.company.shop.config.SessionIdConfig;
import com.company.shop.entity.Goods;
import com.company.shop.service.GoodsService;
import com.company.shop.service.UserService;
import com.haulmont.cuba.security.app.Authentication;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.inject.Inject;
import javax.swing.plaf.PanelUI;

@Component
public class CommandUtils {
    @Inject private ButtonUtils buttonUtils;
    @Inject private InlineButton inlineButton;
    @Inject private UserService userService;
    @Inject private GoodsService goodsService;
    @Inject private LangConfig langConfig;
    @Inject private SessionIdConfig sId;

    SendMessage sendMessage = new SendMessage();
    EditMessageText editMessageText = new EditMessageText();
    DeleteMessage deleteMessage = new DeleteMessage();


    public SendMessage message(String text, String id, Update update){
        if (text.equals("/start")){
            sendMessage.setText("Assalomu alaykum .\n" +
                    "xo'sh kelibsiz bizning online magazinimzga");
            sendMessage.setChatId(id);
            sendMessage.setReplyMarkup(inlineButton.markup(inlineButton.collection(inlineButton.row(
                    inlineButton.button("начать", "start")
            ))));

            /// create User
            userService.createUser(id, update.getMessage().getFrom().getFirstName(), update.getMessage().getFrom().getLastName(), update.getMessage().getFrom().getUserName());

            return sendMessage;
        }
        return null;
    }

    public EditMessageText start(Update update){
        Message message = update.getCallbackQuery().getMessage();
        EditMessageText editMessageText = new EditMessageText();

        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setText("Выберите язык");
        editMessageText.setReplyMarkup(inlineButton.markup(inlineButton.collection(inlineButton.row(
                inlineButton.button("Руский", "ru"),
                inlineButton.button("Узбекский", "uz")
        ))));
        return editMessageText;
    }


    public EditMessageText goods(Update update){
        Message message = update.getCallbackQuery().getMessage();
        if (langConfig.getLang().equals("uz")){
            editMessageText.setText("Tovarlarimiz bilan tanishib chiqing. \n" + "Marhamat .");
            editMessageText.setChatId(String.valueOf(message.getChatId()));
            editMessageText.setMessageId(message.getMessageId());
            editMessageText.setReplyMarkup(inlineButton.markup(buttonUtils.goodsCollection()));
            return editMessageText;
        }else if (langConfig.getLang().equals("ru")){
            editMessageText.setText("Познакомьтесь с нашими товарами.\n" +
                    "пожалуйста .");
            editMessageText.setChatId(String.valueOf(message.getChatId()));
            editMessageText.setMessageId(message.getMessageId());
            editMessageText.setReplyMarkup(inlineButton.markup(buttonUtils.goodsCollection()));
            return editMessageText;
        }
        return null;
    }

    public EditMessageReplyMarkup category(Update update){
        String data = update.getCallbackQuery().getData();
        Message message = update.getCallbackQuery().getMessage();
        EditMessageReplyMarkup edit = new EditMessageReplyMarkup();
        edit.setChatId(message.getChatId().toString());
        edit.setMessageId(message.getMessageId());
        if (data.startsWith("goods#Category")){
            String id = data.substring(14);
            System.out.println("date to id " + id);
            int ids = Integer.parseInt(id);
            System.out.println("getGoods default " + sId.getGoodsId());

            if (sId.getGoodsId() == ids) {
                edit.setReplyMarkup(inlineButton.markup(buttonUtils.CategoryCollection(sId.getGoodsId())));
                return edit;
            }else {
                sId.setGoodsId(ids);
                edit.setReplyMarkup(inlineButton.markup(buttonUtils.CategoryCollection(sId.getGoodsId())));
                return edit;
            }
        }
        edit.setReplyMarkup(inlineButton.markup(buttonUtils.CategoryCollection(sId.getGoodsId())));
        return edit;


    }

    public EditMessageReplyMarkup product(Update update){
        String data = update.getCallbackQuery().getData();
        Message message = update.getCallbackQuery().getMessage();
        EditMessageReplyMarkup edit = new EditMessageReplyMarkup();
        edit.setChatId(message.getChatId().toString());
        edit.setMessageId(message.getMessageId());
        if (data.startsWith("category#Product")){
            String id = data.substring(16);
            System.out.println("product to id " + id);
            System.out.println("categoryId  " + sId.getCategoryId());
            int ids = Integer.parseInt(id);
            if (sId.getCategoryId() == ids){
                edit.setReplyMarkup(inlineButton.markup(buttonUtils.productCollection(sId.getCategoryId())));
                return edit;
            }else {
                sId.setCategoryId(ids);
                edit.setReplyMarkup(inlineButton.markup(buttonUtils.productCollection(sId.getCategoryId())));
                return edit;
            }
        }
        edit.setReplyMarkup(inlineButton.markup(buttonUtils.productCollection(sId.getCategoryId())));
        return edit;
    }

    public SendMessage productNextButton(Update update){
        Message message = update.getCallbackQuery().getMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("");
        sendMessage.setReplyMarkup(null);
        return null;
    }

    public SendMessage productBackButton(Update update){
        Message message = update.getCallbackQuery().getMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(".");
        if (langConfig.getLang().equals("uz")){
            sendMessage.setReplyMarkup(inlineButton.markup(inlineButton.collection(inlineButton.row(
                    inlineButton.button("orqaga", "category#"),
                    inlineButton.button("yana", "category#Product")
            ))));
        }else if (langConfig.getLang().equals("ru")){
            sendMessage.setReplyMarkup(inlineButton.markup(inlineButton.collection(inlineButton.row(
                    inlineButton.button("назад", "category#"),
                    inlineButton.button("ешё", "product#delete")
            ))));
        }
        return sendMessage;
    }

    public DeleteMessage deleteMessage(Update update){
        Message message = update.getCallbackQuery().getMessage();
        deleteMessage.setChatId(message.getChatId().toString());
        deleteMessage.setMessageId(message.getMessageId());
        return deleteMessage;
    }


}
