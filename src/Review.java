
public class Review {

    private int reviewId;
    private int countId = 501;
    private int rating;
    private String comment;
    private int customerId;

    public Review(String comment, int rating, int customerId) {
        this.comment = comment;
        this.rating = rating;
        this.customerId = customerId;
        reviewId = countId++;
    }

    public Review(int reviewId, int customerId, int rating, String comment) {
        this.comment = comment;
        this.rating = rating;
        this.customerId = customerId;
        this.reviewId = reviewId;
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

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
    //print method needed

}
