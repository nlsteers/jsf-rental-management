package com.nlsteers.ui.item;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by nlsteers on 10/02/2017.
 */

@SessionScoped
@Named
public class SearchItems implements Serializable{

    private String itemType;

    public String getItemType() {
        return itemType;
    }

    public void setItemType(final String itemType) {
        this.itemType = itemType;
    }


}
