package com.company.shop.service;

import com.company.shop.config.TgConfig;
import com.company.shop.entity.Goods;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.global.FileLoader;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.security.app.Authentication;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(GoodsService.NAME)
public class GoodsServiceBean implements GoodsService {
    @Inject
    private Metadata metadata;
    @Inject private Persistence persistence;
    @Inject private TgConfig tgConfig;
    @Inject private FileLoader fileLoader;
    @Inject
    Authentication authentication;

    @Override
    public List<Goods> getGoods() {
            List<Goods> goods;
            Transaction tx = persistence.createTransaction();
            try {
                TypedQuery<Goods> query = persistence.getEntityManager()
                        .createQuery("select e from shop_Goods e", Goods.class);
                goods = query.getResultList();
                tx.commit();
            } finally {
                tx.end();
            }
            return goods;
    }
}