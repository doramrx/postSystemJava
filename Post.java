import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Post {
    private LocalDateTime moment;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private String title;
    private String content;
    private Integer likes;
    private ArrayList<Comment> comments = new ArrayList<>();

    public Post() {
    }

    public Post(LocalDateTime moment, String title, String content, Integer likes) {
        this.moment = moment;
        this.title = title;
        this.content = content;
        this.likes = likes;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    public int getTotalOfComments() {
        return comments.size();
    }

    public Comment getCommentBySequenceNumber(int sequenceNumber) {
        return comments.get(sequenceNumber);
    }

    public void likePost() {
        this.likes++;
        System.out.println("Total of likes: " + this.likes);
    }

    public void dislikePost() {
        if (this.likes > 0) {
            this.likes--;
            System.out.println("\nPost disliked successfully!\n");
            System.out.println("Total of likes: " + this.likes);
        } else {
            System.out.println("The Post has no likes to remove.");
        }

    }

    public void viewAllComments() {
        if (!comments.isEmpty()) {
            System.out.println("Comments: ");
            for (int i = 0; i < comments.size(); i++) {
                System.out.println("Comment #" + (i + 1));
                System.out.println(comments.get(i).getText());
            }
        } else {
            System.out.println("The Post has no comments yet.");
        }
    }

    @Override
    public String toString() {
        return "\nMoment: " + formatter.format(this.moment) + "\n" +
                "Title: " + this.title + "\n" +
                "Content: " + this.content + "\n" +
                "Likes: " + this.likes + "\n";
    }

}
