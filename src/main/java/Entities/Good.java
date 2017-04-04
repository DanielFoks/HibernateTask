package Entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private int price;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "goods")
    private Set<OrderT> orderTs;

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

    public Set<OrderT> getOrderTs() {
        return orderTs;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOrderTs(Set<OrderT> orderTs) {
        this.orderTs = orderTs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Good)) return false;
        Good good = (Good) o;
        return Objects.equals(id, good.id) &&
                Objects.equals(price, good.price) &&
                Objects.equals(title, good.title) &&
                Objects.equals(orderTs, good.orderTs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, orderTs);
    }
}
