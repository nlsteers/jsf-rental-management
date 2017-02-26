package com.nlsteers.ui.member;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by nlsteers on 10/02/2017.
 */

@SessionScoped
@Named
public class SearchMembers implements Serializable {

    private String lastNameSearch;

    public String getLastName() {
        return lastNameSearch;
    }

    public void setLastName(String lastName) {
        this.lastNameSearch = lastName;
    }
}
