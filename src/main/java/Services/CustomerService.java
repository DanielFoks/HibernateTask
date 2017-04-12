package Services;

import DAO.CustomerDao;
import Entities.Customer;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.List;


public class CustomerService {
    private static CustomerDao customerDao;

    private static final Logger log = Logger.getLogger(CustomerService.class);

    public CustomerService() {
        customerDao = new CustomerDao();
    }

    public void add(Customer customer) {
        try {
            customerDao.openCurrentSessionWithTransaction();
            customerDao.add(customer);
            log.info(customer.toString() + " was added");
            customerDao.closeCurrentSessionWithTransaction();
        } catch (HibernateException e) {
            customerDao.closeCurrentSessionWithRollbackTransaction();
            log.error(customer.toString() + " can not be added");
            log.error(e.getMessage());
        }
    }

    public void delete(Customer customer) {
        try {
            customerDao.openCurrentSessionWithTransaction();
            customerDao.delete(customer);
            log.info(customer.toString() + " was deleted");
            customerDao.closeCurrentSessionWithTransaction();
        } catch (HibernateException e) {
            customerDao.closeCurrentSessionWithRollbackTransaction();
            log.error(customer.toString() + " can not be deleted");
            log.error(e.getMessage());
        }
    }

    public List<Customer> findAll() throws HibernateException {
        customerDao.openCurrentSession();
        List<Customer> customers = customerDao.findAll();
        customerDao.closeCurrentSession();
        return customers;
    }

    public CustomerDao customerDao() {
        return customerDao;
    }

}
