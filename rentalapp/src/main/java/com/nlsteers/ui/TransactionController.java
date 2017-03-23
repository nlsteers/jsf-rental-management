package com.nlsteers.ui;

import com.nlsteers.*;
import com.nlsteers.dao.item.ItemDAO;
import com.nlsteers.dao.member.MemberDAO;
import com.nlsteers.dao.transaction.TransactionDAO;
import com.nlsteers.ui.transaction.EditTransactions;
import com.nlsteers.ui.transaction.QueueTransactions;
import com.nlsteers.ui.transaction.SearchTransactions;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

/**
 * Created by nlsteers on 13/02/2017.
 * Created by nlsteers on 07/02/2017.
 * DAAODSAA
 */


@RequestScoped
@Named
public class TransactionController {


    @Inject
    TransactionDAO transactionDAO; // For accessing the transactions database

    @Inject
    ItemDAO itemDAO; // For accessing the items database

    @Inject
    MemberDAO memberDAO; // For accessing the members database

    @Inject
    SearchTransactions searchTransactions; // For searching transactions

    @Inject
    EditTransactions editTransactions; // For adding/editing transactions

    @Inject
    QueueTransactions queueTransactions; // For queuing transactions

    public void save() {

        if (checkForFive() == 0) { // Check that the user does not have five items already
            if (checkForDoubles() == 0) { // Check that the user has not tried to book the same item twice
                itemDAO.queryUpdate(editTransactions.getTransaction().getItemNo(), true); //Decrement the item count
                editTransactions.getTransaction().setExpired(0); // Set to not expired
                transactionDAO.merge(editTransactions.getTransaction()); // Save the transaction
                FacesContext context = FacesContext.getCurrentInstance(); // This code shows status messages on the UI
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Transaction added"));
            } else if (checkForDoubles() == -1) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating transaction", "The same user cannot check out the same item more than once a week"));
            }
        } else if (checkForFive() == -1) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating transaction", "The same user cannot check out more than five items in a week"));
        }

    }


    public void request() {

        if (checkForFive() == 0) { // Check that the user does not have five items already, queued or otherwise
            if (checkForDoubles() == 0) {
                queueTransactions.add(editTransactions.getTransaction()); // Queue an unavailable item
            } else if (checkForDoubles() == -1) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating transaction request", "The same user cannot request the same item more than once a week"));
            }
        } else if (checkForFive() == -1) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating transaction request", "The same user cannot request more than five items in a week"));
        }

    }


    private int checkForDoubles() {

        List<SimpleTransaction> simpleTransactions = new ArrayList<SimpleTransaction>();

        //create a list of simple transactions that contain
        for (Transaction t : transactionDAO.queryLastSevenDays()) {
            simpleTransactions.add(new SimpleTransaction(t.getItemNo(), t.getMemberNo()));
        }

        //add the queue transactions
        for (Transaction q : queueTransactions.gettQ()) {

            simpleTransactions.add(new SimpleTransaction(q.getItemNo(), q.getMemberNo()));
        }

        SimpleTransaction stToAdd = new SimpleTransaction(editTransactions.getTransaction().getItemNo(), editTransactions.getTransaction().getMemberNo());

        for (SimpleTransaction st : simpleTransactions) {

            if (st.getItemNo().equals(stToAdd.getItemNo()) && st.getMemberNo().equals(stToAdd.getMemberNo())) {
                System.out.println("There are duplicates.");
                return -1;
            }
        }
        return 0;
    }


    private int checkForFive() {

        List<SimpleTransaction> simpleTransactions = new ArrayList<SimpleTransaction>();

        //create a list of simple transactions
        for (Transaction t : transactionDAO.queryLastSevenDays()) {
            simpleTransactions.add(new SimpleTransaction(t.getItemNo(), t.getMemberNo()));
        }
        //add the queue transactions
        for (Transaction q : queueTransactions.gettQ()) {
            simpleTransactions.add(new SimpleTransaction(q.getItemNo(), q.getMemberNo()));
        }

        Integer memberToSearch = editTransactions.getTransaction().getMemberNo();

        int i = 0;

        for (SimpleTransaction st : simpleTransactions) {
            if (memberToSearch.equals(st.getMemberNo())) {
                i++;
                if (i == 5) {
                    return -1;
                }
            }
        }
        return 0;
    }


    public void remove(Transaction t) {
        Boolean dec = false;
        itemDAO.queryUpdate(t.getItemNo(), dec);
        transactionDAO.remove(t);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction deleted", "Successfully deleted transaction " + t.getTransactionNo()));
    }

    public void preRenderViewEvent() {
        if (editTransactions.getTransaction() == null) {
            initializeTransaction();
        }
    }

    private void initializeTransaction() {
        if (editTransactions.getTransactionNumber() == null) {
            editTransactions.setTransaction(new Transaction());
            return;
        }
        Transaction t = transactionDAO.find(editTransactions.getTransactionNumber());
        editTransactions.setTransaction(t);
    }


    @Produces
    @Named
    public List<Transaction> getTransactions() {
        voidOldTransactions();
        if (searchTransactions.getTransactionDate() == null) {
            return transactionDAO.queryAll();
        } else {
            return transactionDAO.queryTransactionAfter(searchTransactions.getTransactionDate());
        }
    }

    private void voidOldTransactions() {
        int x = -7;
        Calendar cal = GregorianCalendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, x);
        Date sevenDays = cal.getTime();
        List<Transaction> transactionList = transactionDAO.queryAll();

        int i = 0;

        for (Transaction t : transactionList) {
            if (t.getTransactionDate().compareTo(sevenDays) < 0 && t.getExpired() != 1) {
                i++;
                System.out.println("There are " + i + " expired transactions that need attention");
                itemDAO.queryUpdate(t.getItemNo(), false); //increment the item count
                t.setExpired(1); // This transaction has been voided
                transactionDAO.merge(t); // Save the transaction
            }
        }
    }

    public List<SelectItem> getItemNamesAndNumbers() {

        List<SelectItem> items = new ArrayList<SelectItem>();
        List<Item> itemList = itemDAO.queryAvailable();
        for (Item item : itemList) {
            items.add(new SelectItem(item.getItemNo(), item.getItemName()));
        }
        return items;
    }

    public List<SelectItem> getUnavailableItemNamesAndNumbers() {

        List<SelectItem> items = new ArrayList<SelectItem>();
        List<Item> itemList = itemDAO.queryUnavailable();
        for (Item item : itemList) {
            items.add(new SelectItem(item.getItemNo(), item.getItemName()));
        }
        return items;
    }

    public List<SelectItem> getUserNamesAndNumbers() {

        List<SelectItem> members = new ArrayList<SelectItem>();
        List<Member> memberList = memberDAO.queryAll();
        for (Member member : memberList) {
            String first = member.getFirstName();
            String last = member.getLastName();
            String name = first + " " + last;
            members.add(new SelectItem(member.getMemberNo(), name));
        }
        return members;
    }


}
