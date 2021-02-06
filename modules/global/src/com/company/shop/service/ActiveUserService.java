package com.company.shop.service;

public interface ActiveUserService {
    String NAME = "shop_ActiveUserService";

    void createNoActive(String noActive, String firstName, String lastName, String userName, String userId);
    void deleteNoActive(String id);

}