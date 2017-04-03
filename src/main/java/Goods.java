import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private int price;

    @ManyToMany(mappedBy = "goods")
    private Set<Orders> orders;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", orders=" + orders +
                '}';
    }

    public static void addGoodToDB(Goods good,Session session){
        session.save(good);
    }

    public static void deleteGoodFromDB(Goods good,Session session){
        session.delete(good);
    }

    public static List<Goods> goodsArrayList(Session session){
        Criteria criteriaGoods = session.createCriteria(Goods.class);
        return criteriaGoods.list();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Goods)) return false;
        Goods goods = (Goods) o;
        return Objects.equals(id, goods.id) &&
                Objects.equals(price, goods.price) &&
                Objects.equals(title, goods.title) &&
                Objects.equals(orders, goods.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, orders);
    }
}
