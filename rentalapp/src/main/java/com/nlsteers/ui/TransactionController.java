package com.nlsteers.ui;

import com.nlsteers.Item;
import com.nlsteers.Member;
import com.nlsteers.SimpleItem;
import com.nlsteers.Transaction;
import com.nlsteers.dao.item.ItemDAO;
import com.nlsteers.dao.member.MemberDAO;
import com.nlsteers.dao.transaction.TransactionDAO;
import com.nlsteers.ui.transaction.EditTransactions;
import com.nlsteers.ui.transaction.SearchTransactions;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nlsteers on 13/02/2017.
 * Created by nlsteers on 07/02/2017.
 * DAAODSAA
 */


@RequestScoped
@Named
public class TransactionController {


    @Inject
    TransactionDAO transactionDAO;

    @Inject
    ItemDAO itemDAO;

    @Inject
    MemberDAO memberDAO;

    @Inject
    SearchTransactions searchTransactions;

    @Inject
    EditTransactions editTransactions;

    public void save() {
        Boolean dec = true;
        itemDAO.queryUpdate(editTransactions.getTransaction().getItemNo(), dec);
        transactionDAO.merge(editTransactions.getTransaction());
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage("Successfully created transaction"));
    }

    public void remove(Transaction t) {
        Boolean dec = false;
        itemDAO.queryUpdate(t.getItemNo(), dec);
        transactionDAO.remove(t);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Transaction deleted: " + t.getTransactionNo()));

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
        if (searchTransactions.getTransactionDate() == null){
            return transactionDAO.queryAll();
        } else {
            return transactionDAO.queryTransactionAfter(searchTransactions.getTransactionDate());
        }
    }

    public List<SelectItem> getItemNamesAndNumbers(){

        List<SelectItem> items = new ArrayList<SelectItem>();
        List<Item> itemList = itemDAO.queryAvailable();
        for(Item item: itemList){
            items.add(new SelectItem(item.getItemNo(), item.getItemName()));
        }
        return items;
    }

    public List<SelectItem> getUserNamesAndNumbers(){

        List<SelectItem> members = new ArrayList<SelectItem>();
        List<Member> memberList = memberDAO.queryAll();
        for(Member member: memberList){
            String first = member.getFirstName();
            String last = member.getLastName();
            String name = first + " " + last;
            members.add(new SelectItem(member.getMemberNo(), name));
        }
        return members;
    }




}
