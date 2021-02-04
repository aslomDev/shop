package com.company.shop.config.utils;

import com.company.shop.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.inject.Inject;

@Component
public class Lang {
    @Inject private UserService userService;

    public String getLang(Update update){
        String id = update.getCallbackQuery().getFrom().getId().toString();
        return userService.getLang(id);
    }

    public String getUser(Update update){
        String id = update.getCallbackQuery().getFrom().getId().toString();
        return id;
    }

    public Integer getGoods(Update update){
        String id = update.getCallbackQuery().getFrom().getId().toString();
        return userService.getGoods(id);
    }

    public Integer getCategory(Update update){
        String id = update.getCallbackQuery().getFrom().getId().toString();
        return userService.getCategory(id);
    }

    public Integer getItem(Update update){
        String id = update.getCallbackQuery().getFrom().getId().toString();
        return userService.getItem(id);
    }

    public boolean getout(Update update){
        String id = update.getCallbackQuery().getFrom().getId().toString();
        return userService.getOut(id);
    }

    public boolean getOneContent(Update update){
        String id = update.getCallbackQuery().getFrom().getId().toString();
        return userService.getIsOneContent(id);
    }

}
