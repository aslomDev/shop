package com.company.shop.entity;

import com.haulmont.cuba.core.entity.BaseIntegerIdEntity;
import com.haulmont.cuba.core.entity.HasUuid;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Table(name = "SHOP_ACTIVE_USER")
@Entity(name = "shop_ActiveUser")
public class ActiveUser extends BaseIntegerIdEntity {
    private static final long serialVersionUID = -4240731050955738857L;

    @Column(name = "noActiveUser")
    private String noActive;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userId", unique = true)
    private String userId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNoActive() {
        return noActive;
    }

    public void setNoActive(String noActive) {
        this.noActive = noActive;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}