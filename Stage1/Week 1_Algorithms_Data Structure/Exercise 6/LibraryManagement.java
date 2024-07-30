import java.util.Arrays;
import java.util.List;

class Book {
    private final int bookId;
    private final String title;
    private final String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

public class LibraryManagement {

    // Linear search
    public static Book linearSearch(List<Book> books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Binary search
    public static Book binarySearch(List<Book> books, String title) {
        int left = 0;
        int right = books.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int comparison = books.get(mid).getTitle().compareToIgnoreCase(title);

            if (comparison == 0) {
                return books.get(mid);
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
            new Book(1, "GG", "Harry"),
            new Book(2, "Lia", "Lee"),
            new Book(3, "Atom", "Ginny"),
            new Book(4, "Pride and Prejudice", "Jane Austen"),
            new Book(5, "Holmes", "Rutherford")
        );

        String searchTitle = "Lia";

        Book resultLinear = linearSearch(books, searchTitle);
        System.out.println("Linear Search Result: " + resultLinear);

        books.sort((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));

        Book resultBinary = binarySearch(books, searchTitle);
        System.out.println("Binary Search Result: " + resultBinary);
    }
}
