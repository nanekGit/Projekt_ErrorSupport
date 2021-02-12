package pl.edu.wszib.support.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.support.dao.iErrorDAO;
import pl.edu.wszib.support.model.Application;
import pl.edu.wszib.support.model.Error;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class ErrorDAOImpl implements iErrorDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Error> getAllErrors() {
        Session session = this.sessionFactory.openSession();
        Query<Error> query = session.createQuery("FROM pl.edu.wszib.support.model.Error");
        List<Error> errors = query.getResultList();
        session.close();
        return errors;
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public List<Error> getAllErrorsForApplication(Application app) {
        Session session = this.sessionFactory.openSession();
        Query<Error> query = session.createQuery("FROM pl.edu.wszib.support.model.Error WHERE app_id = :id");
        query.setParameter("id", app.getId());
        List<Error> errors = query.getResultList();
        session.close();
        return errors;
    }

    @Override
    public Error getErrorByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Error> query = session.createQuery("FROM pl.edu.wszib.support.model.Error WHERE id = :id");
        query.setParameter("id", id);
        Error result = null;
        try{
            result = query.getSingleResult();
        }catch (NoResultException e){
            //Error not found
        }
        session.close();
        return result;
    }

    @Override
    public boolean persistError(Error error) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(error);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateError(Error error) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(error);
            tx.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            if(tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
        return false;
    }
}
