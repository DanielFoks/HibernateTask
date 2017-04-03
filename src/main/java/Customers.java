import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                ", password='" + password + '\'' +
                '}';
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
