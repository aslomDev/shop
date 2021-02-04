package com.company.shop.service;

import com.company.shop.entity.Users;
import org.telegram.telegrambots.meta.api.objects.User;

public interface UserService {
    String NAME = "shop_UserService";

    void createUser(String id, String firstName, String lastName, String userName);
    void lang(String id, String lang);
    String getLang(String id);
    void createGoods(String id, Integer goodsId);
    Integer getGoods(String id);
    void createCategory(String id, Integer categoryId);
    Integer getCategory(String id);
    void createItem(String id, Integer item);
    Integer getItem(String id);
    void createOutCFalse(String id);
    void createOutCTrue(String id);
    boolean getOut(String id);
    void createIsOneTrueContent(String id);
    void createIsOneFalseContent(String id);
    boolean getIsOneContent(String id);
}