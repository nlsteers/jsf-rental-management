package com.nlsteers.ui.member;

import com.nlsteers.Member;
import org.omnifaces.cdi.ViewScoped;

import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by nlsteers on 20/02/2017.
 * DAAODSAA
 *
 * View scoped injected bean, view scoped for use only on the member-edit view.
 *
 * This class gets and sets Members from the member-edit view of the app
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
