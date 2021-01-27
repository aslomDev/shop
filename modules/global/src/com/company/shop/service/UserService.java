package com.company.shop.service;

public interface UserService {
    String NAME = "shop_UserService";

    void createUser(String id, String firstName, String lastName, String userName);
}