import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Console;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Main {

    private static final String fNEW_LINE = System.getProperty("line.separator");

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();

            Console console = System.console();

            boolean back;

        do {

            back = false;

            console.printf("1. Administrator");
            console.printf(fNEW_LINE);
            console.printf("2. Customer");
            console.printf(fNEW_LINE);
            console.printf("3. Exit");
            console.printf(fNEW_LINE);

            int chose = 0;
            try {
                chose = Integer.parseInt(console.readLine("You are:"));
            }catch (NumberFormatException e){
               e.printStackTrace();
            }

            if (chose==1){
                do {
                console.printf("1. Customers");
                    console.printf(fNEW_LINE);
                console.printf("2. Goods");
                    console.printf(fNEW_LINE);
                console.printf("3. Orders");
                    console.printf(fNEW_LINE);
                console.printf("4. Back");
                    console.printf(fNEW_LINE);
                int table = Integer.parseInt(console.readLine("Select the table to edit:"));
                    console.printf(fNEW_LINE);
                switch (table) {
                    case 1:
                        editCustomers(session, console);
                            break;
                            case 2:
                        editGoods(session,console);
                                break;
                            case 3:
                        editOrders(session,console);
                                break;
                            default:
                        back=true;
                                break;
                        }
                }while (!back);
                back = false;

            }else if (chose==2){

                List<Customers> customers = Customers.customersArrayList(session);
                int tmp = 1;
                for (Customers customer : customers) {
                    console.printf(tmp++ + ". " + customer.getFio());
                    console.printf(fNEW_LINE);
                }
                tmp = Integer.parseInt(console.readLine("Select your number:"));
                console.printf(fNEW_LINE);
                Customers customer = customers.get(tmp-1);
                if (customer.checkPassword(console.readPassword("Enter password:"))){
                    console.printf(fNEW_LINE);
                    List<Goods> goods = Goods.goodsArrayList(session);
                    Set<Goods> goodsSet = new HashSet<>();
                    tmp=1;
                    for (Goods good:goods){
                        console.printf(tmp++ + ". " + good.getTitle()+" ("+good.getPrice()+")");
                        console.printf(fNEW_LINE);
                    }
                    console.printf(fNEW_LINE);
                    console.printf("Select items for the order");
                    console.printf(fNEW_LINE);
                    do{
                     tmp = Integer.parseInt(console.readLine("Add goods(number):"));
                        console.printf(fNEW_LINE);
                     goodsSet.add(goods.get(tmp-1));
                        if (console.readLine("Done (Y/N): ").toUpperCase().equals("Y")){
                            console.printf(fNEW_LINE);
                            back = true;
                        }
                    }while (!back);
                    back = false;

                    Orders orders = new Orders(customer,goodsSet);
                    Orders.addOrderToDB(orders,session);
                }else {
                    console.printf("The password is incorrect");
                    console.printf(fNEW_LINE);
                    back = true;
                }
            }

            else {
                back=true;

            }
}while (!back);
            console.printf("GOODBYE!!!");
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    private static void editCustomers(Session session, Console console) {
        int tmp = 1;
        boolean back;
        do {
            back = false;
            List<Customers> customers = Customers.customersArrayList(session);
                for (Customers customer : customers) {
                    console.printf(tmp++ + ". " + customer.getFio());
                    console.printf(fNEW_LINE);
                }
                console.printf("1. Add:");
            console.printf(fNEW_LINE);
                console.printf("2. Select customer");
            console.printf(fNEW_LINE);
                console.printf("3. Back");
            console.printf(fNEW_LINE);
                tmp = Integer.parseInt(console.readLine("Select action:"));
            console.printf(fNEW_LINE);

            if (tmp == 1) {
                Customers customer = new Customers();
                customer.setFio(console.readLine("Enter full name: "));
                console.printf(fNEW_LINE);
                customer.setPassword(String.valueOf(console.readPassword("Enter password: ")));
                console.printf(fNEW_LINE);
                Customers.addCustomerToDB(customer, session);
            } else if (tmp == 2) {
                tmp = Integer.parseInt(console.readLine("Select customer number:"));
                console.printf(fNEW_LINE);
                if (console.readLine("Delete? (Y/N):").toUpperCase().equals("Y")) {
                    Customers.deleteCustomerFromDB(customers.get(tmp - 1), session);
                    console.printf(fNEW_LINE);
                } else if (console.readLine("Delete? (Y/N):").toUpperCase().equals("N")) {
                    back = true;
                    console.printf(fNEW_LINE);
                }
            } else if (tmp == 3) {
                back = true;
                console.printf(fNEW_LINE);
            }
        }while (!back);
    }

    private static void editGoods(Session session, Console console) {

        int tmp = 1;
        boolean back;
        do {
            back = false;
            List<Goods> goodsList = Goods.goodsArrayList(session);

            for (Goods good : goodsList) {
                console.printf(tmp++ + ". " + good.getTitle());
            }
            console.printf("1. Add:");
            console.printf(fNEW_LINE);
            console.printf("2. Select good:");
            console.printf(fNEW_LINE);
            console.printf("3. Back");
            console.printf(fNEW_LINE);
            tmp = Integer.parseInt(console.readLine("Select action:"));
            console.printf(fNEW_LINE);

            if (tmp == 1) {
                Goods good = new Goods();
                good.setTitle(console.readLine("Enter title:"));
                console.printf(fNEW_LINE);
                good.setPrice(Integer.parseInt(console.readLine("Enter price:")));
                console.printf(fNEW_LINE);
                Goods.addGoodToDB(good,session);
            } else if (tmp == 2) {
                tmp = Integer.parseInt(console.readLine("Select good number:"));
                console.printf(fNEW_LINE);
                if (console.readLine("Delete? (Y/N):").toUpperCase().equals("Y")) {
                    console.printf(fNEW_LINE);
                    Goods.deleteGoodFromDB(goodsList.get(tmp - 1), session);
                } else if (console.readLine("Delete? (Y/N):").toUpperCase().equals("N")) {
                    back = true;
                    console.printf(fNEW_LINE);
                }
            } else if (tmp == 3) {
                back = true;
                console.printf(fNEW_LINE);
            }
        }while (!back);
    }

    private static void editOrders(Session session, Console console) {

        int tmp = 1;
        boolean back;
        do {
            back = false;

            List<Orders> ordersList = Orders.ordersArrayList(session);

            for (Orders order : ordersList) {
                console.printf(tmp++ + ". " + order.getCustomer().getFio());
                for (Goods good:order.getGoods()) {
                    console.printf("- "+good.getTitle()+" ("+good.getPrice()+")");
                }
            }
            console.printf("1. Select good:");
            console.printf(fNEW_LINE);
            console.printf("2. Back");
            console.printf(fNEW_LINE);
            tmp = Integer.parseInt(console.readLine("Select action:"));
            console.printf(fNEW_LINE);
            if (tmp == 1) {
                tmp = Integer.parseInt(console.readLine("Select order number:"));
                console.printf(fNEW_LINE);
                if (console.readLine("Delete? (Y/N):").toUpperCase().equals("Y")) {
                    Orders.deleteOrderFromDB(ordersList.get(tmp - 1), session);
                    console.printf(fNEW_LINE);
                } else if (console.readLine("Delete? (Y/N):").toUpperCase().equals("N")) {
                    back = true;
                    console.printf(fNEW_LINE);
                }
            } else if (tmp == 2) {
                back = true;
            }
        }while (!back);
    }
}
