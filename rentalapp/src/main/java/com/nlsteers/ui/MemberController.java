package com.nlsteers.ui;

/**
 * Created by nlsteers on 06/02/2017.
 * DAAODSAA
 */


import com.nlsteers.Member;
import com.nlsteers.dao.member.MemberDAO;
import com.nlsteers.ui.member.EditMembers;
import com.nlsteers.ui.member.SearchMembers;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


import java.util.List;

/**
 * Controller for the Member UI page
 * Created by nlsteers on 07/02/2017.
 * DAAODSAA
 */
@RequestScoped
@Named
public class MemberController {

    @Inject
    MemberDAO memberDAO; //Inject the member data access object

    @Inject
    SearchMembers searchMembers; // Inject the search params for member

    @Inject
    EditMembers editMembers; // Inject the edit params

    public void save() { // saves a member
        memberDAO.merge(editMembers.getMember());
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new FacesMessage("Successfully created/edited member " + editMembers.getMember().getFirstName() + " " + editMembers.getMember().getLastName()));
    }

    public void remove(Member mem) { // Removes a member
        memberDAO.remove(mem);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Successfully deleted member " + mem.getFirstName() + " " + mem.getLastName()));

    }

    public void preRenderViewEvent() { //Initialise the member view
        if (editMembers.getMember() == null) {
            initializeMember();
        }
    }

    private void initializeMember() { // Initialise the member object
        if (editMembers.getMemberNumber() == null) {
            editMembers.setMember(new Member());
            return;
        }
        Member mem = memberDAO.find(editMembers.getMemberNumber());
        editMembers.setMember(mem);
    }

    @Produces
    @Named
    public List<Member> getSearchedLastName() { // Searches based on last name
        if (searchMembers.getLastName() == null || searchMembers.getLastName().equals("")) {
            return memberDAO.queryAll();
        } else {
            return memberDAO.queryNameSearch(searchMembers.getLastName());
        }
    }
}

