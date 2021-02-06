package com.company.shop.service;

import com.company.shop.entity.ActiveUser;
import com.company.shop.entity.Users;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(ActiveUserService.NAME)
public class ActiveUserServiceBean implements ActiveUserService {
    @Inject private UserService userService;

    @Inject
    private Metadata metadata;
    @Inject private Persistence persistence;

    @Override
    public void createNoActive(String noActive, String firstName, String lastName, String userName, String userId) {
        Transaction tx = persistence.createTransaction();
        try {
            if (userService.getUserId(userId) != null){
                if (getActiveUserId(userId) != null){
                    System.out.println("user yes");
                }else {
                    EntityManager em = persistence.getEntityManager();
                    ActiveUser users = metadata.create(ActiveUser.class);
                    users.setNoActive(noActive);
                    users.setFirstName(firstName);
                    users.setLastName(lastName);
                    users.setUserName(userName);
                    users.setUserId(userId);
                    em.persist(users);
                    tx.commit();
                }
            }else {
                System.out.println("user mavjud!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            tx.end();
        }
    }

    public ActiveUser getActiveUserId(String id) {
        Transaction tx = persistence.createTransaction();
        try {
            TypedQuery<ActiveUser> queryA = persistence.getEntityManager()
                    .createQuery("select s from shop_ActiveUser s where s.userId = :userId", ActiveUser.class);
            queryA.setParameter("userId", id);
            return queryA.getFirstResult();
        } finally {
            tx.end();
        }
    }

    public void deleteNoActive(String id){
        Transaction tx = persistence.createTransaction();
        try {
            if (getActiveUserId(id) != null){
                ActiveUser users = getActiveUserId(id);
                EntityManager em = persistence.getEntityManager();
                em.remove(users);
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
}