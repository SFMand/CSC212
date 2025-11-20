package models;

import structures.*;

public class Product {

    private int productId;
    private static int countId = 151;
    private String name;
    private double price;
    private int stock;
    private double averageRating;
    private int reviewCount;
    private List<Review> reviews;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.productId = countId++;
        this.stock = stock;
        this.reviews = new LinkedList<>();
    }

    public Product(int productId, String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.productId = productId;
        this.stock = stock;
        this.reviews = new LinkedList<>();
    }

    public void addReview(Review r) {
        reviews.insert(r);
        double score = averageRating * reviewCount;
        score += r.getRating();
        reviewCount++;
        averageRating = score / reviewCount;

    }

    // setter/getters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public double getAverageRating() {
        return averageRating;
    }

    // Print method needed
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product Id: ").append(productId);
        sb.append("\nName: ").append(name);
        sb.append("\nPrice: ").append(price);
        sb.append("\nStock: ").append(stock);
        sb.append("\nAverage Rating: ").append(getAverageRating());
        return sb.toString();
    }

}
