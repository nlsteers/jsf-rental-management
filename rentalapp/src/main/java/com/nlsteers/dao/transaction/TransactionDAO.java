package com.nlsteers.dao.transaction;


import com.nlsteers.Transaction;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by nlsteers on 13/02/2017.
 * DAAODSAA
 * Transaction Data access object
 */

@Stateless
public class TransactionDAO {

    // Load session context
    @PersistenceContext
    private EntityManager entityManager;

    //Finds a transaction
    public Transaction find(Integer number) {
        return entityManager.find(Transaction.class, number);
    }

    //Adds of updates a transaction if the transaction already exists
    public Transaction merge(Transaction t) {
        return entityManager.merge(t);
    }

    /*
    public void persist(Transaction t) {
        entityManager.persist(t);
    }
    */

    // Removes a transaction
    public void remove(Transaction t) {
        Transaction attached = find(t.getTransactionNo());
        entityManager.remove(attached);
    }

    // Get all transactions
    public List<Transaction> queryAll() {
        TypedQuery<Transaction> query = entityManager
                .createQuery("select e from Transaction e order by transactionNo desc", Transaction.class);
        return query.getResultList();
    }

    // Get all transactions after specified date
    public List<Transaction> queryTransactionAfter(Date transactionDate) {
        TypedQuery<Transaction> query = entityManager
                .createQuery("select e from Transaction e where e.transactionDate >= :transactionDate order by transactionDate desc", Transaction.class);
        query.setParameter("transactionDate", transactionDate);
        return query.getResultList();
    }

    // Get the last seven days of transactions
    public List<Transaction> queryLastSevenDays() {
        int x = -7;
        Calendar cal = GregorianCalendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, x);
        Date sevenDays = cal.getTime();
        TypedQuery<Transaction> query = entityManager
                .createQuery("select e from Transaction e where e.transactionDate >= :transactionDate order by transactionDate desc", Transaction.class);
        query.setParameter("transactionDate", sevenDays);
        return query.getResultList();
    }
}
