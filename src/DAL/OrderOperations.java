package DAL;

import structures.*;
import models.*;
import java.time.LocalDate;

public class OrderOperations {
    BinaryTree<Integer, Order> orderById;

    public OrderOperations() {
        orderById = new AVLTree<>();
    }

    public boolean addOrder(Order o) {
        return orderById.insert(o.getOrderId(), o);
    }

    public Order searchOrderById(int id) {
        Order o = null;
        if (orderById.findKey(id)) {
            o = orderById.retrieve();
        }
        return o;
    }

    public List<Order> getOrdersBetweenDates(LocalDate start, LocalDate end) {
        List<Order> orders = new LinkedList<>();
        orderById.findRoot();
        traverseOrders(start, end, orders);
        return orders;
    }

    public void traverseOrders(LocalDate start, LocalDate end, List<Order> result) {
        if (orderById.findLeft()) {
            traverseOrders(start, end, result);
            orderById.findParent();
        }
        Order o = orderById.retrieve();
        if (o.getOrderDate().isAfter(start) && o.getOrderDate().isBefore(end)) {
            result.insert(o);
        }

        if (orderById.findRight()) {
            traverseOrders(start, end, result);
            orderById.findParent();
        }
    }

    public void printSortedId(TraverseOrder t) {
        orderById.traverse(t);
    }

}
