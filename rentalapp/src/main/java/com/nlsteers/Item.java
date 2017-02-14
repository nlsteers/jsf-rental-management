package com.nlsteers;

/**
 * Created by nlsteers on 05/02/2017.
 */


import javax.persistence.*;
import java.io.Serializable;

/**
 * Transaction JPA entity.
 */

@Entity
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_no")
    private Integer itemNo;

    @Column(name = "item_name", length = 50)
    private String itemName;

    @Column(name = "item_type", length = 50)
    private String itemType;

    @Column(name = "number_available")
    private Integer numberAvailable;


    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getNumberAvailable() {
        return numberAvailable;
    }

    public void setNumberAvailable(Integer numberAvailable) {
        this.numberAvailable = numberAvailable;
    }
}
