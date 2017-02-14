package com.nlsteers.ui;

/**
 * Created by nlsteers on 06/02/2017.
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
 */
@RequestScoped
@Named
public class MemberController {

    @Inject
    MemberDAO memberDAO;

    @Inject
    SearchMembers searchMembers;

    @Inject
    EditMembers editMembers;


    public void save() {
        memberDAO.merge(editMembers.getMember());
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
                context.addMessage(null, new FacesMessage("Successfully created new member " + editMembers.getMember().getFirstName() + " " + editMembers.getMember().getLastName()));
    }


    public void remove(Member mem) {
        memberDAO.remove(mem);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Successfully deleted member " + mem.getFirstName() + " " + mem.getLastName()));
    }

    public void preRenderViewEvent() {
        if (editMembers.getMember() == null) {
            initializeMember();
        }
    }

    private void initializeMember() {
        if (editMembers.getMemberNumber() == null) {
            editMembers.setMember(new Member());
            return;
        }
        Member mem = memberDAO.find(editMembers.getMemberNumber());
        editMembers.setMember(mem);
    }


    @Produces
    @Named
    public List<Member> getMembers() {
        if (searchMembers.getJoinedAfter() == null) {
            return memberDAO.queryAll();
        } else {
            return memberDAO.queryJoinedAfter(searchMembers.getJoinedAfter());
        }
    }

}

