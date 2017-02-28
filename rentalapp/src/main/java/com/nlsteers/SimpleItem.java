package com.nlsteers;

/**
 * Created by nlsteers on 07/02/2017.
 * DAAODSAA
 */

public class SimpleItem {


    private Integer itemNo;

    private String itemName;

    public SimpleItem(String itemName, Integer itemNo) {
        this.itemName = itemName;
        this.itemNo = itemNo;
    }

    public Integer getItemNo() {
        return itemNo;
    }

    private void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    private void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
