package com.nlsteers.dao.member;

import com.nlsteers.Member;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by nlsteers on 10/02/2017.
 * DAAODSAA
 * Data access object for the Member class
 */

@Stateless
public class MemberDAO {

    // Load the session context
    @PersistenceContext
    private EntityManager entityManager;

    // Finds a member
    public Member find(Integer number) {
        return entityManager.find(Member.class, number);
    }

    // Adds or updates a Member if Member already exists
    public Member merge(Member member) {
        return entityManager.merge(member);
    }


    // Removes a Member
    public void remove(Member member) {
        Member attached = find(member.getMemberNo());
        entityManager.remove(attached);
    }

    // Returns all Members
    public List<Member> queryAll() {
        TypedQuery<Member> query = entityManager
                .createQuery("select e from Member e", Member.class);
        return query.getResultList();
    }

    // Returns Members based on last name search
    public List<Member> queryNameSearch(String lastName) {
        TypedQuery<Member> query = entityManager
                .createQuery("select e from Member e where upper(e.lastName) = upper(:lastName)", Member.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }
}
