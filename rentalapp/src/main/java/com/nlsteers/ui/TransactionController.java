package com.nlsteers.ui;

import com.nlsteers.*;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        if(checkForDoubles() == 0){
            Boolean dec = true;
            itemDAO.queryUpdate(editTransactions.getTransaction().getItemNo(), dec);
            transactionDAO.merge(editTransactions.getTransaction());
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success","Transaction added"));
        } else if (checkForDoubles() ==  -1){
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating transaction","The same user cannot check out the same item more than once a week"));
        }

    }

    private int checkForDoubles(){

        List<Transaction> transactionList = transactionDAO.queryLastSevenDays();

        List<SimpleTransaction> simpleTransactions = new ArrayList<SimpleTransaction>();

        for(Transaction t: transactionList){
            simpleTransactions.add(new SimpleTransaction(t.getItemNo(), t.getMemberNo()));
        }

        SimpleTransaction stToAdd = new SimpleTransaction(editTransactions.getTransaction().getItemNo(), editTransactions.getTransaction().getMemberNo());

        for(SimpleTransaction st: simpleTransactions){

            if (st.getItemNo().equals(stToAdd.getItemNo()) && st.getMemberNo().equals(stToAdd.getMemberNo())){
                System.out.println("There are duplicates.");
                return -1;
            }
        }
        return 0;
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
