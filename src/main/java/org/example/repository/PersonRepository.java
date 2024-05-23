package org.example.repository;

import org.example.config.SessionFactoryHibernate;
import org.example.entity.PersonEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class PersonRepository implements RepositoryInterface<PersonEntity> {

    private final SessionFactoryHibernate sessionFactory;

    @Autowired
    public PersonRepository(SessionFactoryHibernate sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //session.close() закрывает сессию и не загружает данные ManyToMany. Как закрыть сессию по окончании
    @Override
    public List<PersonEntity> findAll() throws Exception {
        Session session = sessionFactory.sessionFactory().openSession();
        List<PersonEntity> entityList = session.createQuery("SELECT a FROM PersonEntity a", PersonEntity.class).getResultList();
        return entityList;
    }

    @Override
    public PersonEntity findById(long id) throws Exception {
        Session session = sessionFactory.sessionFactory().openSession();
        PersonEntity personEntity = session.get(PersonEntity.class, id);
        return personEntity;

    }

    @Override
    public void save(PersonEntity personEntity) throws Exception {
        Session session = sessionFactory.sessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.persist(personEntity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(PersonEntity personEntity) throws Exception {
        Session session = sessionFactory.sessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.merge(personEntity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException();
        }
        session.close();
    }

    @Override
    public void delete(long id) throws Exception {
        Session session = sessionFactory.sessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.remove(session.get(PersonEntity.class, id));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException();
        }
        session.close();
    }
}