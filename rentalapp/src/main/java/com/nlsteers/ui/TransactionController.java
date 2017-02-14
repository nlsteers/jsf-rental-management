package com.nlsteers.ui;

import com.nlsteers.Transaction;
import com.nlsteers.dao.transaction.TransactionDAO;
import com.nlsteers.ui.transaction.SearchTransactions;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by nlsteers on 13/02/2017.
 */


@RequestScoped
@Named
public class TransactionController {


    @Inject
    TransactionDAO transactionDAO;

    @Inject
    SearchTransactions searchTransactions;


    @Produces
    @Named
    public List<Transaction> getTransactions() {
        if (searchTransactions.getTransactionDate() == null){
            return transactionDAO.queryAll();
        } else {
            return transactionDAO.queryTransactionAfter(searchTransactions.getTransactionDate());
        }
    }

}
