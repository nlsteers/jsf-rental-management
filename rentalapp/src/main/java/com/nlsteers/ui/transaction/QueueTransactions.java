package com.nlsteers.ui.transaction;

import com.nlsteers.Transaction;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by nlsteers on 20/02/2017.
 * DAAODSAA
 *
 * Sessions scoped injected bean, session scoped for use only on the rental-view view.
 *
 * This class manages the queue of transactions
 */


@SessionScoped
@Named
public class QueueTransactions implements Serializable{

    private Queue<Transaction> tQ = new LinkedList<Transaction>();

    public void add (Transaction t){
        tQ.add(t);
        System.out.println("Transaction queued.");
    }

    public Queue<Transaction> gettQ() {
        return tQ;
    }

    public Transaction pop (){
        return tQ.poll();
    }

    public Transaction peek (){
        return tQ.peek();
    }
}
