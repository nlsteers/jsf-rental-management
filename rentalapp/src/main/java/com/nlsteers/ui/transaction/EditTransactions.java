package com.nlsteers.ui.transaction;

import com.nlsteers.Transaction;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by nlsteers on 20/02/2017.
 * DAAODSAA
 *
 * View scoped injected bean, view scoped for use only on the rental-new and rental-request views.
 *
 * This class gets and sets Members from the rental-new and rental-request views of the app
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
