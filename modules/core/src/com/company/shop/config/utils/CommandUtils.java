package com.company.shop.config.utils;

import com.company.shop.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.inject.Inject;

@Component
public class CommandUtils {
    @Inject private ButtonUtils buttonUtils;
    @Inject private InlineButton inlineButton;
    @Inject private UserService userService;
    @Inject private Lang lang;

    /// methods
    SendMessage sendMessage = new SendMessage();
    EditMessageText editMessageText = new EditMessageText();
    DeleteMessage deleteMessage = new DeleteMessage();
    EditMessageReplyMarkup editReply = new EditMessageReplyMarkup();


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
        if (lang.getLang(update).equals("uz")){

            editMessageText.setText("Tovarlarimiz bilan tanishib chiqing. \n" + "Marhamat .");
            editMessageText.setChatId(String.valueOf(message.getChatId()));
            editMessageText.setMessageId(message.getMessageId());
            editMessageText.setReplyMarkup(inlineButton.markup(buttonUtils.goodsCollection(lang.getLang(update), lang.getUser(update))));
            return editMessageText;
        }
        else if (lang.getLang(update).equals("ru")){
            editMessageText.setText("Познакомьтесь с нашими товарами.\n" +
                    "пожалуйста .");
            editMessageText.setChatId(String.valueOf(message.getChatId()));
            editMessageText.setMessageId(message.getMessageId());
            editMessageText.setReplyMarkup(inlineButton.markup(buttonUtils.goodsCollection(lang.getLang(update), lang.getUser(update))));
            return editMessageText;
        }
        return null;
    }

    public EditMessageReplyMarkup category(Update update){
        String data = update.getCallbackQuery().getData();
        Message message = update.getCallbackQuery().getMessage();
        editReply.setChatId(message.getChatId().toString());
        editReply.setMessageId(message.getMessageId());
        if (data.startsWith("goods#Category")) {
            String id = data.substring(14);
            int ids = Integer.parseInt(id);
            userService.createGoods(lang.getUser(update), ids);
        }
        editReply.setReplyMarkup(inlineButton.markup(buttonUtils.categoryCollection(lang.getGoods(update), lang.getUser(update), lang.getLang(update))));
        return editReply;


    }

    public SendMessage productNextButton(Update update) {
        Message message = update.getCallbackQuery().getMessage();
        String data = update.getCallbackQuery().getData();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(".");
        if (data.startsWith("category#next*")) {
            if (lang.getLang(update).equals("uz")){
              if (lang.getout(update)){
                  sendMessage.setReplyMarkup(inlineButton.markup(inlineButton.collection(inlineButton.row(
                          inlineButton.button("orqaga", "goods#"),
                          inlineButton.button("yana", "category#next*")
                  ))));
              }else if (!lang.getout(update)){
                  sendMessage.setReplyMarkup(inlineButton.markup(inlineButton.collection(
                          inlineButton.row(
                                  inlineButton.button("orqaga", "goods#")
                          ))));
              }
            } else if (lang.getLang(update).equals("ru")){
                if (lang.getout(update)){
                    sendMessage.setReplyMarkup(inlineButton.markup(inlineButton.collection(inlineButton.row(
                            inlineButton.button("назад", "goods#"),
                            inlineButton.button("ешё", "category#next*")
                    ))));
                }else if (!lang.getout(update)){
                    sendMessage.setReplyMarkup(inlineButton.markup(inlineButton.collection(inlineButton.row(
                            inlineButton.button("назад", "goods#")
                    ))));
                }
            }
        } else  {
            if (lang.getLang(update).equals("uz")) {
                if (lang.getout(update)){
                    sendMessage.setReplyMarkup(inlineButton.markup(inlineButton.collection(inlineButton.row(
                            inlineButton.button("orqaga", "goods#"),
                            inlineButton.button("yana", "category#next*")
                    ))));
                }else if (!lang.getout(update)){
                    sendMessage.setReplyMarkup(inlineButton.markup(inlineButton.collection(inlineButton.row(
                            inlineButton.button("orqaga", "goods#")
                    ))));
                }
            } else if (lang.getLang(update).equals("ru")){
                if (lang.getout(update)){
                    sendMessage.setReplyMarkup(inlineButton.markup(inlineButton.collection(inlineButton.row(
                            inlineButton.button("назад", "goods#"),
                            inlineButton.button("ешё", "category#next*" + 1)
                    ))));
                }else if (!lang.getout(update)){
                    sendMessage.setReplyMarkup(inlineButton.markup(inlineButton.collection(inlineButton.row(
                            inlineButton.button("назад", "goods#")
                    ))));
                }
            }
        }
        return sendMessage;

    }

    public DeleteMessage deleteMessage(Update update){
        Message message = update.getCallbackQuery().getMessage();
        deleteMessage.setChatId(message.getChatId().toString());
        deleteMessage.setMessageId(message.getMessageId());
        return deleteMessage;
    }


    public EditMessageReplyMarkup contentIsNull(Update update){
        Message message = update.getCallbackQuery().getMessage();
        editReply.setChatId(message.getChatId().toString());
        editReply.setMessageId(message.getMessageId());
        editReply.setReplyMarkup(inlineButton.markup(buttonUtils.contentIsNull(lang.getLang(update))));
        return editReply;
    }

    public EditMessageText productIsNull(Update update){
        Message message = update.getCallbackQuery().getMessage();
        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        if (lang.getLang(update).equals("uz")){
            editMessageText.setText("uzur hozircha content mavjud emas!");
            editMessageText.setReplyMarkup(inlineButton.markup(inlineButton.collection(inlineButton.row(
                    inlineButton.button("oqaga", "goods#")
            ))));
        }else if (lang.getLang(update).equals("ru")){
            editMessageText.setText("контент не найдено!");
            editMessageText.setReplyMarkup(inlineButton.markup(inlineButton.collection(inlineButton.row(
                    inlineButton.button("назад", "goods#")
            ))));
        }
        return editMessageText;

    }


}
