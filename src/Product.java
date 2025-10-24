
import java.io.*;

public class Product {

    private int productId;
    private static int countId = 151;
    private String name;
    private double price;
    private int stock;
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
        //write to csv file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("assets/reviews.csv", true));
            writer.newLine();
            writer.write(r.getReviewId() + "," + productId + "," + r.getCustomerId() + "," + r.getRating() + "," + "\""+r.getComment()+"\"");
            writer.close();
            reviews.insert(r);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public double averageRating() {
        double avg = 0;
        if (!reviews.empty()) {
            int count = 0;
            double sum = 0;
            reviews.findFirst();
            while (!reviews.last()) {
                count++;
                sum += reviews.retrieve().getRating();
                reviews.findNext();
            }
            count++;
            sum += reviews.retrieve().getRating();
            avg = sum / count;
        }
        return avg;
    }

    public void printReviews() {
        System.out.println("=== REVIEWS OF " + getName().toUpperCase() + " ===");
        reviews.findFirst();
        while (true) {
            System.out.println(reviews.retrieve());
            System.out.println("-------------------------");
            if (reviews.last()) {
                break;
            }
            reviews.findNext();
        }
    }

    //  setter/getters
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

    //Print method needed
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product Id: ").append(productId);
        sb.append("\nName: ").append(name);
        sb.append("\nPrice: ").append(price);
        sb.append("\nStock: ").append(stock);
        return sb.toString();
    }

}
