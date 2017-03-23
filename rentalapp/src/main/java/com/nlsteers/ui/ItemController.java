package com.nlsteers.ui;

import com.nlsteers.Item;
import com.nlsteers.dao.item.ItemDAO;
import com.nlsteers.ui.item.EditItems;
import com.nlsteers.ui.item.SearchItems;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Controller for the Item UI page
 * * Created by nlsteers on 07/02/2017.
 * DAAODSAA
 */
@RequestScoped
@Named
public class ItemController {
    @Inject
    ItemDAO itemDAO; // Inject the item data access object

    @Inject
    SearchItems searchItems; // Inject the search params

    @Inject
    EditItems editItems; // Inject the update/create params

    private List<Item> itemsList; // Initial constructor for the items data table

    public void save() { // Saves an item
        itemDAO.merge(editItems.getItem());
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage("Successfully created/edited item " + editItems.getItem().getItemName()));
    }

    public void remove(Item item) { // Removes an item
        itemDAO.remove(item);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Successfully deleted item " + item.getItemNo() + " " + item.getItemName()));
    }

    public void preRenderViewEvent() { //Initialise the view
        if (editItems.getItem() == null) {
            initializeItem();
        }
    }

    private void initializeItem() { // Initialise the item object
        if (editItems.getItemNumber() == null) {
            editItems.setItem(new Item());
            return;
        }
        Item item = itemDAO.find(editItems.getItemNumber());
        editItems.setItem(item);
    }


    public void loadItems(){
        itemsList = itemDAO.queryAll();
    } //populates the data table

    public void getItemByName(){ // Filters the table by name search
        if (searchItems.getItemName() == null || searchItems.getItemName().equals("")) {
            itemsList = itemDAO.queryAll();
        } else {
            itemsList = itemDAO.queryItemName(searchItems.getItemName());
        }
    }


    public void getItemByNumber(){ // Filters the table by Number search
        if (searchItems.getItemNumber() == null || searchItems.getItemNumber() == 0) {
            itemsList = itemDAO.queryAll();
        } else {
            itemsList = itemDAO.queryItemNumber(searchItems.getItemNumber());
        }
    }


    @Produces
    @Named
    public List<Item> getItems() {
       return itemsList;
    } // Named factory method for showing the data

}
