
public class Review {

    private int score;
    private String comment;
    private Customer reviewer;

    public Review(String comment, int score, Customer reviewer) {
        this.comment = comment;
        this.score = score;
        this.reviewer = reviewer;
    }

    public void editReview(String comment, int score) {
        this.comment = comment;
        this.score = score;

    }
    //  setter/getters

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Customer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Customer reviewer) {
        this.reviewer = reviewer;
    }
        //print method needed

}
