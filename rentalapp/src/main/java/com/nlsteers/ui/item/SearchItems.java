package com.nlsteers.ui.item;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by nlsteers on 10/02/2017.
 * DAAODSAA
 *
 * Session scoped injected bean, session scoped for use across the users session to persist search details.
 *
 * This class allows for searching of Items in the item view
 */

@SessionScoped
@Named
public class SearchItems implements Serializable{

    private String itemType;

    private String itemName;

    private Integer itemNumber;

    public String getItemType() {
        return itemType;
    }


    public void setItemType(final String itemType) {
        this.itemType = itemType; //Final for non-transitive mutability
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(final String itemName) {
        this.itemName = itemName; //Final for non-transitive mutability
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(final Integer itemNumber) {
        this.itemNumber = itemNumber; //Final for non-transitive mutability
    }
}
