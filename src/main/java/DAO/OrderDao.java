package DAO;

import DAO.Interfaces.OrderDaoInterface;
import Entities.OrderT;
import Utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by foksd on 04.04.2017.
 */
public class OrderDao implements OrderDaoInterface<OrderT> {

    private Session currentSession;

    private Transaction currentTransaction;

    public OrderDao(){}

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

}

    @Override
    public void add(OrderT entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void delete(OrderT entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<OrderT> findAll() {
        Criteria criteria = getCurrentSession().createCriteria(OrderT.class);
        return criteria.list();
    }
}
