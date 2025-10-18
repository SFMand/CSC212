
public class Product {

    private int productId;
    private String name;
    private double price;
    private int stock;
    private List<Review> reviews;

    public Product(String name, double price, int productId, int stock) {
        this.name = name;
        this.price = price;
        this.productId = productId;
        this.stock = stock;
        this.reviews = new LinkedList<>();
    }

    public void addReview(Review r) {
        reviews.insert(r);
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

}
