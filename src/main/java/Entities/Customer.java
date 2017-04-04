package Entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String fio;

    private String password;

    @OneToMany(mappedBy = "customer")
    private Set<OrderT> orderTs;



    public Customer(){}

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<OrderT> getOrderTs() {
        return orderTs;
    }

    public void setOrderTs(Set<OrderT> orderTs) {
        this.orderTs = orderTs;
    }

    public boolean checkPassword(char[] password){
        if (String.valueOf(password).equals(this.password)){
            return true;
        }
        else return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(fio, customer.fio) &&
                Objects.equals(password, customer.password) &&
                Objects.equals(orderTs, customer.orderTs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, password, orderTs);
    }
}
