package com.example.domain;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class ContactService {

    private EntityManager em;

    public ContactService(EntityManager someEntityManager) {
        this.em = someEntityManager;
    }

    public void save(Contact c) {
        em.persist(c);
    }

    public Contact update(Contact c) {
        return em.merge(c);
    }

    public Contact find(long id) {
        return em.find(Contact.class, id);
    }

    public List<Contact> findAll() {
        Query query = em.createQuery("SELECT c FROM Contact c");
        List<Contact> resultList = query.getResultList();
        return resultList;
    }

    public List<Contact> findAllNamed() {
        TypedQuery<Contact> findAll = em.createNamedQuery("findAll", Contact.class);
        List<Contact> resultList = findAll.getResultList();
        return resultList;
    }

    public void delete(long id) {
        Contact contact = find(id);
        if (contact != null) {
            em.remove(contact);
        }
    }

    public Contact updateFirstName(Contact c, String newFirstName) {
        c.setFirstName(newFirstName);
        return c;
    }

    public Contact updateFirstName(long id, String newFirstName) {
        Contact contact = find(id);
        contact.setFirstName(newFirstName);
        return contact;
    }

    public void save(Department d) {
        em.persist(d);
    }

//    ....

}
