package com.nlsteers.ui.transaction;

import com.nlsteers.Transaction;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by nlsteers on 26/02/2017.
 * DAAODSAA
 */

@ViewScoped
@Named
public class EditTransactions implements Serializable {


    private Integer tNumber;

    private Transaction transaction;

    public Integer getTransactionNumber() {
        return tNumber;
    }

    public void setTransactionNumber(final Integer tNumber) {
        this.tNumber = tNumber;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(final Transaction transaction) {
        this.transaction = transaction;
    }


}
