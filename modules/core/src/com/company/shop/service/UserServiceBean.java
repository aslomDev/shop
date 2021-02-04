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

    @Override
    public void lang(String id, String lang) {
        Transaction tx = persistence.createTransaction();
        try {
            if (getUserId(id) != null){
                Users users = getUserId(id);
                EntityManager em = persistence.getEntityManager();;
                users.setLang(lang);
                em.merge(users);
                tx.commit();
            }else {
                System.out.println("user mavjud!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            tx.end();
        }
    }

    @Override
    public String getLang(String id) {
        Transaction tx = persistence.createTransaction();
        if (getUserId(id) != null){
            Users users = getUserId(id);
            return users.getLang();
        }
        System.out.println("id topilmadi " + id);
        return null;
    }

    @Override
    public void createGoods(String id, Integer goodsId) {
        Transaction tx = persistence.createTransaction();
        try {
            if (getUserId(id) != null){
                Users users = getUserId(id);
                EntityManager em = persistence.getEntityManager();;
                users.setGoodsId(goodsId);
                em.merge(users);
                tx.commit();
            }else {
                System.out.println("user mavjud!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            tx.end();
        }
    }

    @Override
    public Integer getGoods(String id) {
        if (getUserId(id) != null){
            Users users = getUserId(id);
            return users.getGoodsId();
        }
        System.out.println("id topilmadi " + id);
        return null;
    }

    @Override
    public void createCategory(String id, Integer categoryId) {
        Transaction tx = persistence.createTransaction();
        try {
            if (getUserId(id) != null){
                Users users = getUserId(id);
                EntityManager em = persistence.getEntityManager();;
                users.setCategoryId(categoryId);
                em.merge(users);
                tx.commit();
            }else {
                System.out.println("user mavjud!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            tx.end();
        }
    }

    @Override
    public Integer getCategory(String id) {
        if (getUserId(id) != null){
            Users users = getUserId(id);
            return users.getCategoryId();
        }
        System.out.println("id topilmadi " + id);
        return null;
    }

    @Override
    public void createItem(String id, Integer item) {
        Transaction tx = persistence.createTransaction();
        try {
            if (getUserId(id) != null){
                Users users = getUserId(id);
                EntityManager em = persistence.getEntityManager();;
                users.setItem(item);
                em.merge(users);
                tx.commit();
            }else {
                System.out.println("user mavjud!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            tx.end();
        }
    }

    @Override
    public Integer getItem(String id) {
        if (getUserId(id) != null){
            Users users = getUserId(id);
            return users.getItem();
        }
        System.out.println("id topilmadi " + id);
        return null;
    }

    @Override
    public void createOutCFalse(String id) {
        Transaction tx = persistence.createTransaction();
        try {
            if (getUserId(id) != null){
                Users users = getUserId(id);
                EntityManager em = persistence.getEntityManager();
                users.setOutContent(false);
                em.merge(users);
                tx.commit();
            }else {
                System.out.println("user mavjud!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            tx.end();
        }
    }

    @Override
    public void createOutCTrue(String id) {
        Transaction tx = persistence.createTransaction();
        try {
            if (getUserId(id) != null){
                Users users = getUserId(id);
                EntityManager em = persistence.getEntityManager();
                users.setOutContent(true);
                em.merge(users);
                tx.commit();
            }else {
                System.out.println("user mavjud!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            tx.end();
        }
    }


    @Override
    public boolean getOut(String id) {
        if (getUserId(id) != null){
            Users users = getUserId(id);
            return users.getOutContent();
        }
        System.out.println("id topilmadi " + id);
        return false;
    }

    @Override
    public void createIsOneTrueContent(String id) {
        Transaction tx = persistence.createTransaction();
        try {
            if (getUserId(id) != null){
                Users users = getUserId(id);
                EntityManager em = persistence.getEntityManager();
                users.setOneContent(true);
                em.merge(users);
                tx.commit();
            }else {
                System.out.println("user mavjud!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            tx.end();
        }
    }

    @Override
    public void createIsOneFalseContent(String id) {
        Transaction tx = persistence.createTransaction();
        try {
            if (getUserId(id) != null){
                Users users = getUserId(id);
                EntityManager em = persistence.getEntityManager();
                users.setOneContent(false);
                em.merge(users);
                tx.commit();
            }else {
                System.out.println("user mavjud!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            tx.end();
        }
    }

    @Override
    public boolean getIsOneContent(String id) {
        if (getUserId(id) != null){
            Users users = getUserId(id);
            return users.getOneContent();
        }
        System.out.println("id topilmadi " + id);
        return false;
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