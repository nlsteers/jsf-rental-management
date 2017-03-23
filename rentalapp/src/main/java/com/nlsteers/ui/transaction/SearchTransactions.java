package com.nlsteers.ui.transaction;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by nlsteers on 10/02/2017.
 * DAAODSAA
 *
 * Session scoped injected bean, session scoped for use across the users session to persist search details.
 *
 * This class allows for searching of transactions in the index view
 */


@SessionScoped
@Named
public class SearchTransactions implements Serializable{

    private Date transactionDate;


    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(final Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
