package com.nlsteers.ui.member;

import com.nlsteers.Member;
import org.omnifaces.cdi.ViewScoped;

import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by nlsteers on 10/02/2017.
 */

@ViewScoped
@Named
public class EditMembers implements Serializable{


    private Integer memberNumber;

    private Member member;

    public Integer getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(final Integer memberNumber) {
        this.memberNumber = memberNumber;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(final Member member) {
        this.member = member;
    }
}