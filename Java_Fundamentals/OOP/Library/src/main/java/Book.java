import java.time.LocalDate;
import java.util.UUID;

public class Book {
    private final String title;
    private final String id;
    private final String author;
    private boolean available = true;
    private LocalDate deadlineDate;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        id = UUID.randomUUID().toString();
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public boolean isAvailable() {
        return available;
    }

}
