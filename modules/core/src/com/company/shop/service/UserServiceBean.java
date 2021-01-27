package com.company.shop.service;

import com.company.shop.config.TgConfig;
import com.company.shop.entity.Users;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.global.FileLoader;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.security.app.Authentication;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(UserService.NAME)
public class UserServiceBean implements UserService {

    @Inject
    private Metadata metadata;
    @Inject private Persistence persistence;
    @Inject private TgConfig tgConfig;
    @Inject private FileLoader fileLoader;
    @Inject Authentication authentication;



    @Override
    public void createUser(String id, String firstName, String lastName, String userName) {
        Transaction tx = persistence.createTransaction();
        try {
            if (getUserId(id) != null){
                System.out.println("user mavjud!");
            }else {
                EntityManager em = persistence.getEntityManager();
                Users tgUser = metadata.create(Users.class);
                tgUser.setUserId(id);
                tgUser.setFirstName(firstName);
                tgUser.setLastName(lastName);
                tgUser.setUserName(userName);
                em.persist(tgUser);
                tx.commit();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            tx.end();
        }
    }



    public Users getUserId(String id) {
        Transaction tx = persistence.createTransaction();
        try {
            TypedQuery<Users> queryA = persistence.getEntityManager()
                    .createQuery("select s from shop_Users s where s.userId = :userId", Users.class);
            queryA.setParameter("userId", id);
            return queryA.getFirstResult();
        } finally {
            tx.end();
        }
    }
}