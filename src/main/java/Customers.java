import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.*;

@Entity
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String fio;

    private String password;

    @OneToMany(mappedBy = "customer")
    private Set<Orders> orders;



    public Customers(){}

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

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public boolean checkPassword(char[] password){
        if (String.valueOf(password).equals(this.password)){
            return true;
        }
        else return false;
    }


    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customers)) return false;
        Customers customers = (Customers) o;
        return Objects.equals(id, customers.id) &&
                Objects.equals(fio, customers.fio) &&
                Objects.equals(password, customers.password) &&
                Objects.equals(orders, customers.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, password, orders);
    }

    public static void addCustomerToDB(Customers customer,Session session){
        session.save(customer);
    }

    public static void deleteCustomerFromDB(Customers customer,Session session){
session.delete(customer);
    }

    public static List<Customers> customersArrayList(Session session){
        Criteria criteriaCustomer = session.createCriteria(Customers.class);
        return criteriaCustomer.list();
    }

}
