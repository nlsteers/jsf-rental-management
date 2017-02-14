package com.nlsteers.dao.item;

import com.nlsteers.Item;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by nlsteers on 10/02/2017.
 */

@Stateless
public class ItemDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Item> queryAll() {
        TypedQuery<Item> query = entityManager
                .createQuery("select e from Item e", Item.class);
        return query.getResultList();
    }

    public List<Item> queryItemType(String itemType) {
        TypedQuery<Item> query = entityManager
                .createQuery("select e from Item e where e.itemType = :itemType ", Item.class);
        query.setParameter("itemType", itemType);
        return query.getResultList();
    }
}
