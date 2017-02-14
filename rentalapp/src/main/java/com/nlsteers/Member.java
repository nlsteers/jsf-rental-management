package com.nlsteers;

/**
 * Created by nlsteers on 05/02/2017.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Member JPA entity.
 */

@Entity
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Integer memberNo;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "join_date")
    @Temporal(TemporalType.DATE)
    private Date joinDate;

    public Integer getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Integer memberNo) {
        this.memberNo = memberNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(final Date joinDate) {
        this.joinDate = joinDate;
    }


}
