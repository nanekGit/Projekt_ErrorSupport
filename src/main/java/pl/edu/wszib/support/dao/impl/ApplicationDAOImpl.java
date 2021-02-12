package pl.edu.wszib.support.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.support.dao.iApplicationDAO;
import pl.edu.wszib.support.model.Application;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class ApplicationDAOImpl implements iApplicationDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public List<Application> getAllApplications() {
        Session session = this.sessionFactory.openSession();
        Query<Application> query = session.createQuery("FROM pl.edu.wszib.support.model.Application");
        List<Application> apps = query.getResultList();
        session.close();
        return apps;
    }

    @Override
    public Application getApplicationByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Application> query = session.createQuery("FROM pl.edu.wszib.support.model.Application WHERE id = :id");
        query.setParameter("id", id);
        Application result = null;
        try{
            result = query.getSingleResult();
        }catch (NoResultException e){
            //Application not found
        }
        session.close();
        return result;
    }

    @Override
    public boolean persistApplication(Application app) {
        Session session = this.sessionFactory.openSession();
        System.out.println(app);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(app);
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
}
