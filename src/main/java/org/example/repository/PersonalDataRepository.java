package org.example.repository;

import org.example.config.SessionFactoryHibernate;
import org.example.entity.PersonalDataEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonalDataRepository implements RepositoryInterface<PersonalDataEntity> {

    private final SessionFactoryHibernate sessionFactory;

    @Autowired
    public PersonalDataRepository(SessionFactoryHibernate sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<PersonalDataEntity> findAll() throws Exception {
        Session session = sessionFactory.sessionFactory().openSession();
        return session.createQuery("FROM PersonalDataEntity", PersonalDataEntity.class).list();
    }

    @Override
    public PersonalDataEntity findById(long id) throws Exception {
        Session session = sessionFactory.sessionFactory().openSession();
        return session.createQuery("FROM PersonalDataEntity WHERE id=" + id, PersonalDataEntity.class).getSingleResult();
    }

    @Override
    public void save(PersonalDataEntity personalDataEntity) throws Exception {
        Session session = sessionFactory.sessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.persist(personalDataEntity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(PersonalDataEntity personalDataEntity) throws Exception {
        Session session = sessionFactory.sessionFactory().openSession();
        String hql = "UPDATE PersonalDataEntity "
                + "SET login = :login "
                + ", password = :password "
                + ", person.id = :person "
                + " where id = :id";
        MutationQuery query = session.createMutationQuery(hql);
        query.setParameter("login", personalDataEntity.getLogin());
        query.setParameter("password", personalDataEntity.getPassword());
        query.setParameter("person", personalDataEntity.getPerson().getId());
        query.setParameter("id", personalDataEntity.getId());
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(long id) throws Exception {
        Session session = sessionFactory.sessionFactory().openSession();
        String hql = "DELETE FROM PersonalDataEntity WHERE id = :id";
        MutationQuery query = session.createMutationQuery(hql);
        query.setParameter("id", id);
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException();
        }
        session.close();
    }
}
