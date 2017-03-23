package com.nlsteers.ui;

import com.nlsteers.Item;
import com.nlsteers.dao.item.ItemDAO;
import com.nlsteers.ui.item.EditItems;
import com.nlsteers.ui.item.SearchItems;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Controller for the Item UI page
 * * Created by nlsteers on 07/02/2017.
 * DAAODSAA
 */
@ViewScoped
@Named
public class ItemController {

    @Inject
    ItemDAO itemDAO;

    @Inject
    SearchItems searchItems;

    @Inject
    EditItems editItems;

    public void save() {
        itemDAO.merge(editItems.getItem());
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage("Successfully created/edited item " + editItems.getItem().getItemName()));
    }

    public void remove(Item item) {
        itemDAO.remove(item);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Successfully deleted item " + item.getItemNo() + " " + item.getItemName()));
    }

    public void preRenderViewEvent() {
        if (editItems.getItem() == null) {
            initializeItem();
        }
    }

    private void initializeItem() {
        if (editItems.getItemNumber() == null) {
            editItems.setItem(new Item());
            return;
        }
        Item item = itemDAO.find(editItems.getItemNumber());
        editItems.setItem(item);
    }


    public List<Item> getItemByName(){
        System.out.println(searchItems.getItemName());
        return itemDAO.queryItemName(searchItems.getItemName());
    }


    public List<Item> getItemByNumber(){
        System.out.println(searchItems.getItemNumber());
        return itemDAO.queryItemNumber(searchItems.getItemNumber());
    }


    @Produces
    @Named
    public List<Item> getItems() {

         /*if (searchItems.getItemType().equalsIgnoreCase("All") && searchItems.getItemNumber() == null && searchItems.getItemName() == null) {
            return itemDAO.queryAll();
        }*/

        if (searchItems.getItemNumber() == null && searchItems.getItemName() == null) {
            return itemDAO.queryAll();
        }

        if (searchItems.getItemNumber() == 0 && searchItems.getItemName().equals("")) {
            return itemDAO.queryAll();
        }

        if (!searchItems.getItemName().equals("")) {
            return getItemByName();
        }

        if (searchItems.getItemNumber() > 0) {
            return getItemByNumber();
        }

        else {
            return itemDAO.queryAll();

        }

    }







}
