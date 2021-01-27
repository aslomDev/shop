package com.company.shop.service;

import com.company.shop.entity.Product;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(ProductService.NAME)
public class ProductServiceBean implements ProductService {
    @Inject
    private Persistence persistence;

    @Override
    public List<Product> getProduct(Integer id) {
            List<Product> products;
            Transaction tx = persistence.createTransaction();
            try {
                TypedQuery<Product> query = persistence.getEntityManager()
                        .createQuery("select e from shop_Product e where e.category.id = :id", Product.class)
                        .addViewName("product-view");
                query.setParameter("id", id);
                products = query.getResultList();
                tx.commit();
            } finally {
                tx.end();
            }

            return products;
    }
}