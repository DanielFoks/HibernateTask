package Services;


import DAO.GoodDao;
import Entities.Good;

import java.util.List;

public class GoodService {
    private static GoodDao goodDao;

    public GoodService() {
        goodDao = new GoodDao();
    }

    public void add(Good good){
        goodDao.openCurrentSessionWithTransaction();
        goodDao.add(good);
        goodDao.closeCurrentSessionWithTransaction();
    }

    public void delete(Good good){
        goodDao.openCurrentSessionWithTransaction();
        goodDao.delete(good);
        goodDao.closeCurrentSessionWithTransaction();
    }

    public List<Good> findAll(){
        goodDao.openCurrentSession();
        List<Good> goods = goodDao.findAll();
        goodDao.closeCurrentSession();
        return goods;
    }

    public GoodDao goodDao(){
        return goodDao;
    }
}
