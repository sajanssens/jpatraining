package com.example;

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
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }

    public Contact find(long id){
        return em.find(Contact.class, id);
    }

    public List<Contact> findAll(){
        Query query = em.createQuery("SELECT c FROM Contact c");
        List<Contact> resultList = query.getResultList();
        return resultList;
    }

    public List<Contact> findAllNamed(){
        TypedQuery<Contact> findAll = em.createNamedQuery("findAll", Contact.class);
        List<Contact> resultList = findAll.getResultList();
        return resultList;
    }

    public void delete(long id){
        Contact contact = find(id);
        if(contact!=null) {
            em.getTransaction().begin();
            em.remove(contact);
            em.getTransaction().commit();
        }
    }

//    ....

}
