package org.example.repository;

import jakarta.persistence.criteria.*;
import org.example.config.SessionFactoryHibernate;
import org.example.entity.CommunityEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommunityRepository implements RepositoryInterface<CommunityEntity> {
    private final SessionFactoryHibernate sessionFactory;

    @Autowired
    public CommunityRepository(SessionFactoryHibernate sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CommunityEntity> findAll() throws Exception {
        Session session = sessionFactory.sessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CommunityEntity> criteriaQuery = criteriaBuilder.createQuery(CommunityEntity.class);
        criteriaQuery.select(criteriaQuery.from(CommunityEntity.class));
        List<CommunityEntity> entityList = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return entityList;
    }

    @Override
    public CommunityEntity findById(long id) throws Exception {
        Session session = sessionFactory.sessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CommunityEntity> criteriaQuery = criteriaBuilder.createQuery(CommunityEntity.class);
        Root<CommunityEntity> root = criteriaQuery.from(CommunityEntity.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        CommunityEntity communityEntity = session.createQuery(criteriaQuery).getSingleResult();
        session.close();
        return communityEntity;
    }

    @Override
    public void save(CommunityEntity communityEntity) throws Exception {
        Session session = sessionFactory.sessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(communityEntity);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new RuntimeException();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(CommunityEntity communityEntity) throws Exception {
        Session session = sessionFactory.sessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<CommunityEntity> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(CommunityEntity.class);
        Root<CommunityEntity> root = criteriaUpdate.from(CommunityEntity.class);
        Transaction transaction = session.beginTransaction();
        try {
            if (communityEntity.getName() != null) {
                criteriaUpdate.set("name", communityEntity.getName());
            }
            if (communityEntity.getDescription() != null) {
                criteriaUpdate.set("description", communityEntity.getDescription());
            }
            criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), communityEntity.getId()));
            session.createMutationQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new RuntimeException();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(long id) throws Exception {
        Session session = sessionFactory.sessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<CommunityEntity> criteriaDelete = criteriaBuilder.createCriteriaDelete(CommunityEntity.class);
        Root<CommunityEntity> root = criteriaDelete.from(CommunityEntity.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
        try {
            transaction.begin();
            session.createMutationQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException();
        }
        session.close();

    }
}
