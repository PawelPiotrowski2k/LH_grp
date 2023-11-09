package Models;

import java.util.UUID;

public class Book {
    private final String title;
    private final String id;
    private final String author;
    private boolean available = true;


    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        id = UUID.randomUUID().toString();
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

}
