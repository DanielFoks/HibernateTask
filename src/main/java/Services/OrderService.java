package Services;

import DAO.OrderDao;
import Entities.OrderT;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.List;

public class OrderService {
    private static OrderDao orderDao;

    private static final Logger log = Logger.getLogger(OrderService.class);

    public OrderService() {
        orderDao = new OrderDao();
    }

    public void add(OrderT orderT) {
        try {
            orderDao.openCurrentSessionWithTransaction();
            orderDao.add(orderT);
            log.info(orderT.toString() + " was added");
            orderDao.closeCurrentSessionWithTransaction();
        } catch (HibernateException e) {
            orderDao.closeCurrentSessionWithRollbackTransaction();
            log.error(orderT.toString() + " can not be added");
        }
    }

    public void delete(OrderT orderT) {
        try {
            orderDao.openCurrentSessionWithTransaction();
            orderDao.delete(orderT);
            log.info(orderT.toString() + " was deleted");
            orderDao.closeCurrentSessionWithTransaction();
        } catch (HibernateException e) {
            orderDao.closeCurrentSessionWithRollbackTransaction();
            log.error(orderT.toString() + " can not be deleted");
        }

    }

    public List<OrderT> findAll() {
        orderDao.openCurrentSession();
        List<OrderT> orderTs = orderDao.findAll();
        orderDao.closeCurrentSession();
        return orderTs;
    }

    public OrderDao orderDao() {
        return orderDao;
    }
}
