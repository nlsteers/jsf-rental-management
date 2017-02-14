package com.nlsteers.dao.member;

import com.nlsteers.Member;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Created by nlsteers on 10/02/2017.
 */

@Stateless
public class MemberDAO {

    @PersistenceContext
    private EntityManager entityManager;


    public Member find(Integer number) {
        return entityManager.find(Member.class, number);
    }

    public Member merge(Member member) {
        return entityManager.merge(member);
    }

    public void persist(Member member) {
        entityManager.persist(member);
    }

    public void remove(Member member) {
        Member attached = find(member.getMemberNo());
        entityManager.remove(attached);
    }

    public List<Member> queryAll() {
        TypedQuery<Member> query = entityManager
                .createQuery("select e from Member e", Member.class);
        return query.getResultList();
    }

    public List<Member> queryJoinedAfter(Date joinedAfter) {
        TypedQuery<Member> query = entityManager
                .createQuery("select e from Member e where e.joinDate >= :joinedAfter", Member.class);
        query.setParameter("joinedAfter", joinedAfter);
        return query.getResultList();
    }


}
