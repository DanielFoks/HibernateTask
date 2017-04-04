package Services;

import DAO.CustomerDao;
import Entities.Customer;

import java.util.List;

public class CustomerService {
    private static CustomerDao customerDao;

    public CustomerService(){
        customerDao = new CustomerDao();
    }

    public void add(Customer customer){
        customerDao.openCurrentSessionWithTransaction();
        customerDao.add(customer);
        customerDao.closeCurrentSessionWithTransaction();
    }

    public void delete(Customer customer){
        customerDao.openCurrentSessionWithTransaction();
        customerDao.delete(customer);
        customerDao.closeCurrentSessionWithTransaction();
    }

    public List<Customer> findAll(){
        customerDao.openCurrentSession();
        List<Customer> customers = customerDao.findAll();
        customerDao.closeCurrentSession();
        return customers;
    }

    public CustomerDao customerDao(){
        return customerDao;
    }

}
