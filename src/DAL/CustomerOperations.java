package DAL;

import models.Customer;
import structures.*;

public class CustomerOperations {
    BinaryTree<String, Customer> customerKeyName;
    BinaryTree<Integer, Customer> customerKeyId;

    public CustomerOperations() {
        customerKeyName = new AVLTree<>();
        customerKeyId = new AVLTree<>();
    }

    public Customer searchCustomerName(String name) {
        Customer c = null;
        if (customerKeyName.findKey(name)) {
            c = customerKeyName.retrieve();
        }
        return c;
    }

    public Customer searchCustomerId(int id) {
        Customer c = null;
        if (customerKeyId.findKey(id)) {
            c = customerKeyId.retrieve();
        }
        return c;
    }

    public boolean insertCustomer(Customer c) {
        if (customerKeyId.insert(c.getCustomerId(), c)) {
            if (customerKeyName.insert(c.getName(), c)) {
                return true;
            } else {
                customerKeyId.removeKey(c.getCustomerId());
            }
        }
        return false;
    }

    public void printSortedName(TraverseOrder t) {
        customerKeyName.traverse(t);
    }

    public void printSortedId(TraverseOrder t) {
        customerKeyId.traverse(t);
    }

}
