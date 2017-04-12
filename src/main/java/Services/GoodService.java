package Services;


import DAO.GoodDao;
import Entities.Good;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.List;

public class GoodService {
    private static GoodDao goodDao;

    private static final Logger log = Logger.getLogger(GoodService.class);

    public GoodService() {
        goodDao = new GoodDao();
    }

    public void add(Good good) {
        try {
            goodDao.openCurrentSessionWithTransaction();
            goodDao.add(good);
            log.info(good.toString() + " was added");
            goodDao.closeCurrentSessionWithTransaction();
        } catch (HibernateException e) {
            goodDao.closeCurrentSessionWithRollbackTransaction();
            log.error(good.toString() + " can not be added");
        }
    }

    public void delete(Good good) {
        try {
            goodDao.openCurrentSessionWithTransaction();
            goodDao.delete(good);
            log.info(good.toString() + " was deleted");
            goodDao.closeCurrentSessionWithTransaction();
        } catch (HibernateException e) {
            goodDao.closeCurrentSessionWithRollbackTransaction();
            log.error(good.toString() + " can not be deleted");
        }

    }

    public List<Good> findAll() {
        goodDao.openCurrentSession();
        List<Good> goods = goodDao.findAll();
        goodDao.closeCurrentSession();
        return goods;
    }

    public GoodDao goodDao() {
        return goodDao;
    }
}
