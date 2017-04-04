package Services;

import DAO.OrderDao;
import Entities.OrderT;

import java.util.List;

public class OrderService {
    private static OrderDao orderDao;

    public OrderService() {
        orderDao = new OrderDao();
    }

    public void add(OrderT orderT){
        orderDao.openCurrentSessionWithTransaction();
        orderDao.add(orderT);
        orderDao.closeCurrentSessionWithTransaction();
    }

    public void delete(OrderT orderT){
        orderDao.openCurrentSessionWithTransaction();
        orderDao.delete(orderT);
        orderDao.closeCurrentSessionWithTransaction();
    }

    public List<OrderT> findAll(){
        orderDao.openCurrentSession();
        List<OrderT> orderTs = orderDao.findAll();
        orderDao.closeCurrentSession();
        return orderTs;
    }

    public OrderDao orderDao(){
        return orderDao;
    }
}
