
public class Review {

    private int rating;
    private String comment;
    private int customerId;

    public Review(String comment, int score, int customerId) {
        this.comment = comment;
        this.rating = score;
        this.customerId = customerId;
    }

    public void editReview(String comment, int score) {
        this.comment = comment;
        this.rating = score;

    }
    //  setter/getters

    public int getRating() {
        return rating;
    }

    public void setRating(int score) {
        this.rating = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    //print method needed

}
