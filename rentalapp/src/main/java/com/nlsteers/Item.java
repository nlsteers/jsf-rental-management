package com.nlsteers;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Item JPA entity.
 * Created by nlsteers on 07/02/2017.
 * DAAODSAA
 *
 * Item class.
 */

@Entity
public class Item implements Serializable {
    // These annotated variables create the database tables
    @Id //primary key
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
