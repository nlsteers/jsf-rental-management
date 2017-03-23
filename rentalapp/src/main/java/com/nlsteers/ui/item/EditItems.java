package com.nlsteers.ui.item;

import com.nlsteers.Item;
import org.omnifaces.cdi.ViewScoped;

import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by nlsteers on 20/02/2017.
 * DAAODSAA
 *
 * View scoped injected bean, view scoped for use only on the item-edit view.
 *
 * This class gets and sets Items from the item-edit view of the app
 */

@ViewScoped
@Named
public class EditItems implements Serializable{

    private Integer itemNumber;

    private Item item ;

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(final Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(final Item item) {
        this.item = item;
    }

}
