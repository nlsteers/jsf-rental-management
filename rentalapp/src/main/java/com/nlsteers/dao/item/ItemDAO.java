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

    public Item find(Integer number) {
        return entityManager.find(Item.class, number);
    }

    public Item merge(Item i) {
        return entityManager.merge(i);
    }

    public void persist(Item i) {
        entityManager.persist(i);
    }

    public void remove(Item i) {
        Item attached = find(i.getItemNo());
        entityManager.remove(attached);
    }

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
