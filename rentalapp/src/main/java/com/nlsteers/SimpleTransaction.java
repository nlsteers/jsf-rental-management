package com.nlsteers;

/**
 * Created by nlsteers on 07/02/2017.
 * DAAODSAA
 */

public class SimpleTransaction {


    private Integer itemNo;

    private Integer memberNo;


    public SimpleTransaction(Integer itemNo, Integer memberNo) {
        this.itemNo = itemNo;
        this.memberNo = memberNo;
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public Integer getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Integer memberNo) {
        this.memberNo = memberNo;
    }
}
