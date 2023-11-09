import Models.Book;
import Models.User;
import Operations.PenaltyManager;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Backoffice {
    private final static int timeToReturnBook = 14;
    private final Set<Book> books;
    private final Set<User> users;
    private final PenaltyManager penaltyManager;
    private final Map<Book, User> rentedBooks;
    private final Map<Book, LocalDateTime> bookDeadline;

    public Backoffice() {
        penaltyManager = new PenaltyManager();
        books = new HashSet<>();
        users = new HashSet<>();
        rentedBooks = new HashMap<>();
        bookDeadline = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void returnBook(Book book) {
        assert rentedBooks.containsKey(book);
        User returningUser = rentedBooks.get(book);
            if (isAfterDeadline(book)) {
                penaltyManager.addPenaltyPoints(returningUser.getId(), (int) ChronoUnit.DAYS.between(bookDeadline.get(book), LocalDateTime.now()));
                penaltyManager.banProcedure(returningUser);
            }
            rentedBooks.remove(book);
            bookDeadline.remove(book);
            book.setAvailable(true);
    }

    public void rentBook(Book book, User user) {
        if (book.isAvailable() && !penaltyManager.isPenalty(user)) {
            rentedBooks.put(book, user);
            book.setAvailable(false);
                bookDeadline.compute(book, (key, value) -> value == null ? LocalDateTime.now().plusDays(timeToReturnBook): value);
        }
    }
    public boolean isAfterDeadline(Book book){
        return LocalDateTime.now().isAfter(bookDeadline.get(book));
    }


}
