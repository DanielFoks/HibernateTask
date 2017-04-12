package DAO;

import DAO.Interfaces.GoodDaoInterface;
import Entities.Good;
import Utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class GoodDao implements GoodDaoInterface<Good> {

    private Session currentSession;

    private Transaction currentTransaction;

    public GoodDao() {

    }

    public Session openCurrentSession() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void closeCurrentSessionWithRollbackTransaction() {
        currentTransaction.rollback();
        currentSession.close();
    }


    @Override
    public void add(Good entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void delete(Good entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<Good> findAll() {
        Criteria criteria = getCurrentSession().createCriteria(Good.class);
        return criteria.list();
    }
}
