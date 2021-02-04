package com.company.shop.service;

import com.company.shop.entity.ProductContent;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(ProductContentService.NAME)
public class ProductContentServiceBean implements ProductContentService {
    @Inject
    private Persistence persistence;

    @Override
    public List<ProductContent> getProductContent(Integer id) {
            List<ProductContent> productContents;
            Transaction tx = persistence.createTransaction();
            try {
                TypedQuery<ProductContent> query = persistence.getEntityManager()
                        .createQuery("select e from shop_ProductContent e  where e.product.id = :id", ProductContent.class)
                        .addViewName("productContent-view");
                query.setParameter("id", id);
                productContents = query.getResultList();
                tx.commit();
            } finally {
                tx.end();
            }

            return productContents;
        }

    @Override
    public List<ProductContent> getProductContentAll(int offset, int limit) {
        List<ProductContent> productContents;
        Transaction tx = persistence.createTransaction();
        try {
            TypedQuery<ProductContent> query = persistence.getEntityManager()
                    .createQuery("select e from shop_ProductContent e", ProductContent.class)
                    .addViewName("productContent-view");
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            productContents = query.getResultList();
            tx.commit();
        } finally {
            tx.end();
        }

        return productContents;
    }

    @Override
    public List<ProductContent> getProductContentAll2() {
        List<ProductContent> productContents;
        Transaction tx = persistence.createTransaction();
        try {
            TypedQuery<ProductContent> query = persistence.getEntityManager()
                    .createQuery("select e from shop_ProductContent e", ProductContent.class)
                    .addViewName("productContent-view");
            productContents = query.getResultList();
            tx.commit();
        } finally {
            tx.end();
        }

        return productContents;
    }

}