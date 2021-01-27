package com.company.shop.service;

import com.company.shop.entity.Category;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(CategoryService.NAME)
public class CategoryServiceBean implements CategoryService {
    @Inject private Persistence persistence;


    @Override
    public List<Category> getCategory(Integer id) {
        List<Category> categories;
        Transaction tx = persistence.createTransaction();
        try {
            TypedQuery<Category> query = persistence.getEntityManager()
                    .createQuery("select e from shop_Category e where e.goods.id = :id", Category.class)
                    .addViewName("category-view");
                   query.setParameter("id", id);
            categories = query.getResultList();
            tx.commit();
        } finally {
            tx.end();
        }
        return categories;
    }
}