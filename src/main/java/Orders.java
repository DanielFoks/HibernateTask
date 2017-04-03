import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;


    @ManyToMany
    @JoinTable(name="orders_goods")
    private Set<Goods> goods;

    public Orders(){}

    public Orders(Customers customer, Set<Goods> goods) {
        this.customer = customer;
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public Customers getCustomer() {
        return customer;
    }

    public Set<Goods> getGoods() {
        return goods;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public void setGoods(Set<Goods> goods) {
        this.goods = goods;
    }

    public static void addOrderToDB(Orders order, Session session){
        session.save(order);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", customer=" + customer +
                '}';
    }

    public static List<Orders> ordersArrayList(Session session){
        Criteria criteriaOrders = session.createCriteria(Orders.class);
        return criteriaOrders.list();
    }

    public static void deleteOrderFromDB(Orders order,Session session){
        session.delete(order);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id) &&
                Objects.equals(customer, orders.customer) &&
                Objects.equals(goods, orders.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, goods);
    }
}
