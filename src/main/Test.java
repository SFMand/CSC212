package main;


public class Test {

     public static void main(String[] args) {
        ECommerceSystem ec = new ECommerceSystem();
        ec.loadFiles();
        ec.getProductsInRange(200.0, 1000.0).print();
        ec.getTopRatedProducts().print();
        ec.productReviewers(102).print();
        
    }


}
