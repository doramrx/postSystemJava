import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class PostSystem {
    private ArrayList<Post> posts = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public PostSystem() {
        sc = new Scanner(System.in);
    }

    public void startSystem() {
        int op = -1;
        while (op != 0) {
            op = mainMenu();
            switch (op) {
                case 1:
                    addPost();
                    break;
                case 2:
                    deletePost();
                    break;
                case 3:
                    addComment();
                    break;
                case 4:
                    deleteComment();
                    break;
                case 5:
                    likeAPost();
                    break;
                case 6:
                    dislikeAPost();
                    break;
                case 7:
                    viewAllPosts();
                    break;
                case 0:
                    System.out.println("System finished");
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    public int mainMenu() {

        System.out.println("---------------MENU-----------------");
        System.out.println("  1 - Add new Post");
        System.out.println("  2 - Delete a Post");
        System.out.println("  3 - Add a comment to a Post");
        System.out.println("  4 - Delete a comment");
        System.out.println("  5 - Like Post");
        System.out.println("  6 - Dislike Post");
        System.out.println("  7 - View all Posts");
        System.out.println("  0 - Finish Program");
        System.out.println("------------------------------------");

        System.out.println("Choose an option:");
        int op = Integer.parseInt(sc.nextLine());

        return op;
    }

    public void addPost() {
        System.out.println("\nAdding a new Post\n");

        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Content: ");
        String content = sc.nextLine();

        Post post = new Post(LocalDateTime.now(), title, content, 0);
        posts.add(post);

        System.out.println("\nPost added successfully!\n");
    }

    public void deletePost() {
        if (thereIsPosts()) {
            System.out.println("\nSelect a Post to delete: \n");

            viewAllPosts();

            try {
                int n = Integer.parseInt(sc.nextLine());

                if (!isOptionValid(n)) {
                    System.out.println("\n The Post doesn't exist.\n");
                    return;
                }
                posts.remove(n - 1);
                System.out.println("\nPost deleted successfully!\n");
            } catch (NumberFormatException e) {
                System.out.println("You must type a number.\n");
            }
        }
    }

    public void addComment() {
        if (thereIsPosts()) {
            System.out.println("\nSelect a post to comment: \n");

            viewAllPosts();

            try {
                int n = Integer.parseInt(sc.nextLine());

                if (!isOptionValid(n)) {
                    System.out.println("\n The Post doesn't exist.\n");
                    return;
                }

                System.out.println("\nAdding a new comment: \n");
                System.out.print("Type here: ");
                String text = sc.nextLine();

                Comment c = new Comment(text);
                Post p = posts.get(n - 1);
                p.addComment(c);

                System.out.println("\nComment sent successfully!\n");

            } catch (NumberFormatException e) {
                System.out.println("You must type a number.\n");
            }
        }
    }

    public void deleteComment() {
        if (thereIsPosts()) {
            System.out.println("\nSelect a Post to delete a comment: \n");

            viewAllPosts();

            try {
                int nPost = Integer.parseInt(sc.nextLine());

                if (!isOptionValid(nPost)) {
                    System.out.println("\n The Post doesn't exist.\n");
                    return;
                }

                Post p = posts.get(nPost - 1);

                if (p.getTotalOfComments() == 0) {
                    System.out.println("The Post has no comments yet.\n");

                } else {
                    System.out.println("\nSelect a comment to delete: \n");

                    p.viewAllComments();

                    int nComment = Integer.parseInt(sc.nextLine());

                    if (!isOptionValid(nComment)) {
                        System.out.println("The comment doesn't exist.\n");
                        return;
                    }

                    p.removeComment(p.getCommentBySequenceNumber(nComment - 1));

                    System.out.println("\nComment deleted successfully!\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("You must type a number.\n");
            }
        }
    }

    public void likeAPost() {
        if (thereIsPosts()) {
            System.out.println("\nSelect a Post to like: \n");

            viewAllPosts();

            try {
                int n = Integer.parseInt(sc.nextLine());

                if (!isOptionValid(n)) {
                    System.out.println("\n The Post doesn't exist.\n");
                    return;
                }

                Post p = posts.get(n - 1);
                p.likePost();

                System.out.println("\nPost liked successfully!\n");

            } catch (NumberFormatException e) {
                System.out.println("You must type a number.\n");
            }
        }
    }

    public void dislikeAPost() {
        if (thereIsPosts()) {
            System.out.println("\nSelect a Post to dislike: \n");

            viewAllPosts();

            try {
                int n = Integer.parseInt(sc.nextLine());

                if (!isOptionValid(n)) {
                    System.out.println("\n The Post doesn't exist.\n");
                    return;
                }

                Post p = posts.get(n - 1);
                p.dislikePost();

            } catch (NumberFormatException e) {
                System.out.println("You must type a number.\n");
            }
        }
    }

    public void viewAllPosts() {
        if (thereIsPosts()) {
            for (int i = 1; i <= posts.size(); i++) {
                System.out.println("Post #" + i);
                System.out.print(posts.get(i - 1).toString());
                posts.get(i - 1).viewAllComments();
                System.out.println("------------------------------------");
            }
        }
    }

    public boolean isOptionValid(Integer n) {

        if (n <= 0 || n > posts.size()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean thereIsPosts() {
        if (posts.isEmpty()) {
            System.out.println("There is no Post yet.\n");
            return false;
        } else {
            return true;
        }
    }
}
