package DAO;

import DAO.Interfaces.CustomerDaoInterface;
import Entities.Customer;
import Utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class CustomerDao implements CustomerDaoInterface<Customer>{

    private Session currentSession;

    private Transaction currentTransaction;

    public CustomerDao() {
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

    public void closeCurrentSessionWithRollbackTransaction() {
        currentTransaction.rollback();
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

    @Override
    public void add(Customer entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void delete(Customer entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<Customer> findAll() {
        Criteria criteriaCustomer = getCurrentSession().createCriteria(Customer.class);
        return criteriaCustomer.list();
    }
}
