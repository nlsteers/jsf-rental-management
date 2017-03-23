package com.nlsteers.dao.item;

import com.nlsteers.Item;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by nlsteers on 10/02/2017.
 * DAAODSAA
 * Data access object for the Item class
 */

@Stateless
public class ItemDAO {

    // Load the session context
    @PersistenceContext
    private EntityManager entityManager;

    // Find an Item in the Items database
    public Item find(Integer number) {
        return entityManager.find(Item.class, number);
    }

    // Add or Update an Item to/in the Items database
    public Item merge(Item i) {
        return entityManager.merge(i);
    }

    /* UNUSED
    public void persist(Item i) {
        entityManager.persist(i);
    }
    */

    // Removes an Item from the Items database
    public void remove(Item i) {
        Item attached = find(i.getItemNo()); //Call the entity manager
        entityManager.remove(attached); //Delete the item
    }

    // Finds all items, exports them to a List
    public List<Item> queryAll() {
        TypedQuery<Item> query = entityManager
                .createQuery("select e from Item e", Item.class);
        return query.getResultList();
    }

    // Finds all available items, exports them to a List
    public List<Item> queryAvailable() {
        TypedQuery<Item> query = entityManager
                .createQuery("select e from Item e where e.numberAvailable > 0", Item.class);
        return query.getResultList();
    }

    // Finds all unavailable items, exports them to a List
    public List<Item> queryUnavailable() {
        TypedQuery<Item> query = entityManager
                .createQuery("select e from Item e where e.numberAvailable = 0", Item.class);
        return query.getResultList();
    }

    // Takes a Boolean operator, depending on truth value will increment or decrement an Item's availability
    public void queryUpdate(Integer itemNum, Boolean decrement) {
        if (!decrement) {
            Query q = entityManager.createQuery("UPDATE Item SET numberAvailable = numberAvailable + 1 WHERE itemNo = :itemNum");
            q.setParameter("itemNum", itemNum);
            q.executeUpdate();
        } else {
            Query q = entityManager.createQuery("UPDATE Item SET numberAvailable = numberAvailable - 1 WHERE itemNo = :itemNum AND numberAvailable > 0");
            q.setParameter("itemNum", itemNum);
            q.executeUpdate();
        }
    }

    // Returns a set of Items based on the Item type
    public List<Item> queryItemType(String itemType) {
        TypedQuery<Item> query = entityManager
                .createQuery("select e from Item e where e.itemType = :itemType ", Item.class);
        query.setParameter("itemType", itemType);
        return query.getResultList();
    }

    // Returns a set of Items based on the Item name
    public List<Item> queryItemName(String itemName) {
        TypedQuery<Item> query = entityManager
                .createQuery("select e from Item e where upper(e.itemName) = upper(:itemName)", Item.class);
        query.setParameter("itemName", itemName);
        return query.getResultList();
    }

    // Returns a set of Items based on the Item number
    public List<Item> queryItemNumber(Integer itemNumber) {
        TypedQuery<Item> query = entityManager
                .createQuery("select e from Item e where e.itemNo = :itemNumber ", Item.class);
        query.setParameter("itemNumber", itemNumber);
        return query.getResultList();
    }
}
