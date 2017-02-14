package com.nlsteers.dao.transaction;


import com.nlsteers.Transaction;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Created by nlsteers on 13/02/2017.
 */

@Stateless
public class TransactionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Transaction> queryAll() {
        TypedQuery<Transaction> query = entityManager
                .createQuery("select e from Transaction e", Transaction.class);
        return query.getResultList();
    }

    public List<Transaction> queryTransactionAfter(Date transactionDate) {
        TypedQuery<Transaction> query = entityManager
                .createQuery("select e from Transaction e where e.transactionDate >= :transactionDate", Transaction.class);
        query.setParameter("transactionDate", transactionDate);
        return query.getResultList();
    }
}
