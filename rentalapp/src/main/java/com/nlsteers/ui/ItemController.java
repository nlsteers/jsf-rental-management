package com.nlsteers.ui;

import com.nlsteers.Item;
import com.nlsteers.dao.item.ItemDAO;
import com.nlsteers.ui.item.SearchItems;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by nlsteers on 07/02/2017.
 */

@RequestScoped
@Named
public class ItemController {

    @Inject
    ItemDAO itemDAO;

    @Inject
    SearchItems searchItems;

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
