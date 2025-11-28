package models;

public class Review {

    private int reviewId;
    private static int countId = 501;
    private int rating;
    private String comment;
    private Customer reviewCustomer;

    public Review(String comment, int rating, Customer c) {
        this.comment = comment;
        this.rating = rating;
        reviewCustomer = c;
        reviewId = countId++;
    }

    public Review(int reviewId, Customer c, int rating, String comment) {
        this.comment = comment;
        this.rating = rating;
        reviewCustomer = c;
        this.reviewId = reviewId;
    }

    public void editReview(String comment, int score) {
        this.comment = comment;
        this.rating = score;
    }

    // setter/getters

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

    public Customer getReviewCustomer() {
        return reviewCustomer;
    }

    public void setReviewCustomer(Customer reviewCustomer) {
        this.reviewCustomer = reviewCustomer;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Review Id: ").append(reviewId);
        sb.append("\nRating: ").append(rating);
        sb.append("\nComment:").append(comment);
        return sb.toString();
    }

}
