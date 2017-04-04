package Entities;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
public class OrderT {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToMany
    @JoinTable(name="orders_goods")
    private Set<Good> goods;

    public OrderT(){}

    public OrderT(Customer customer, Set<Good> goods) {
        this.customer = customer;
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<Good> getGoods() {
        return goods;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setGoods(Set<Good> goods) {
        this.goods = goods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderT)) return false;
        OrderT orderT = (OrderT) o;
        return Objects.equals(id, orderT.id) &&
                Objects.equals(customer, orderT.customer) &&
                Objects.equals(goods, orderT.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, goods);
    }
}
