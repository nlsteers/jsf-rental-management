package com.nlsteers.ui;

import com.nlsteers.Item;
import com.nlsteers.SimpleItem;
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


    @Produces
    @Named
    public List<Item> getItems() {
        if (searchItems.getItemType() == null) {
            return itemDAO.queryAll();
        } else if (searchItems.getItemType().equalsIgnoreCase("All")) {
            return itemDAO.queryAll();
        }
        else {
            return itemDAO.queryItemType(searchItems.getItemType());
        }
    }




}
