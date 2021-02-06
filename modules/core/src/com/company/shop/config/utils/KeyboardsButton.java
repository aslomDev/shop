package com.company.shop.config.utils;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public class KeyboardsButton {


    public ReplyKeyboardMarkup markup() {
    KeyboardButton button = new KeyboardButton();
    button.setText("hello");

    KeyboardRow rows = new KeyboardRow();

    rows.add(button);

    List<KeyboardRow> rowList = new LinkedList<>();

    rowList.add(rows);

    ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();

    markup.setResizeKeyboard(true);
    markup.setKeyboard(rowList);
    markup.setSelective(true);

    return markup;
    }


}
