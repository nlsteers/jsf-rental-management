package com.nlsteers.dao.transaction;


import com.nlsteers.SimpleTransaction;
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
 */

@Stateless
public class TransactionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Transaction find(Integer number) {
        return entityManager.find(Transaction.class, number);
    }

    public Transaction merge(Transaction t) {
        return entityManager.merge(t);
    }

    public void persist(Transaction t) {
        entityManager.persist(t);
    }

    public void remove(Transaction t) {
        Transaction attached = find(t.getTransactionNo());
        entityManager.remove(attached);
    }


    public List<Transaction> queryAll() {
        TypedQuery<Transaction> query = entityManager
                .createQuery("select e from Transaction e order by transactionNo desc", Transaction.class);
        return query.getResultList();
    }

    public List<Transaction> queryTransactionAfter(Date transactionDate) {
        TypedQuery<Transaction> query = entityManager
                .createQuery("select e from Transaction e where e.transactionDate >= :transactionDate order by transactionDate desc", Transaction.class);
        query.setParameter("transactionDate", transactionDate);
        return query.getResultList();
    }

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
