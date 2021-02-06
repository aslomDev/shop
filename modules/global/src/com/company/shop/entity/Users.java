package com.company.shop.entity;

import com.haulmont.cuba.core.entity.BaseIntegerIdEntity;
import com.haulmont.cuba.core.entity.HasUuid;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Table(name = "SHOP_USERS")
@Entity(name = "shop_Users")
public class Users extends BaseIntegerIdEntity {
    private static final long serialVersionUID = 2905528816081069531L;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "userName")
    private String userName;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "userId", unique = true)
    private String userId;

    @Column(name = "lang")
    private String lang;

    @Column(name = "item")
    private Integer item;

    @Column(name = "goodsId")
    private Integer goodsId;

    @Column(name = "categoryId")
    private Integer categoryId;

    @Column(name = "outContent")
    private Boolean outContent = false;

    @Column(name = "oneContent")
    private Boolean oneContent = false;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getOutContent() {
        return outContent;
    }

    public void setOutContent(Boolean outContent) {
        this.outContent = outContent;
    }

    public Boolean getOneContent() {
        return oneContent;
    }

    public void setOneContent(Boolean oneContent) {
        this.oneContent = oneContent;
    }
}