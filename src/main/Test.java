package main;

import java.time.LocalDate;

import structures.TraverseOrder;

public class Test {

    public static void main(String[] args) {
        ECommerceSystem ec = new ECommerceSystem();
        ec.loadFiles();
        ec.getProductsInRange(200.0, 1000.0).print();
        ec.getTopRatedProducts().print();
        ec.productReviewers(111).traverse(TraverseOrder.IN_ORDER);
       
       
        LocalDate start = LocalDate.parse("2025-03-04");
        LocalDate end = LocalDate.parse("2025-03-08");
        ec.getOrdersBetweenDates(start, end).print();

    }

}
