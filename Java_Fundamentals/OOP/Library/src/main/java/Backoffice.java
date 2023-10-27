import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Backoffice {

    public static Backoffice backoffice = new Backoffice();

    public Book addBook(String title, String author) {
        Book book;
        book = new BookBuilder().setTitle(title).setAuthor(author).build();
        return book;
    }

    public void removeBook(Book book) {
        book = null;
    }

    public User addUser(String name, String surName, String email) {
        User user = new User(name, surName, email);
        return user;
    }

    public void removeUser(User user) {
        user = null;
    }

    public boolean returnBook(Book book, User user) {
        if (user.getListOfRentedBooks().contains(book)) {
            if (LocalDate.now().isAfter(book.getDeadlineDate())) {
                user.setPenaltyPoints((int) ChronoUnit.DAYS.between(LocalDate.now(), book.getDeadlineDate()));
                int penaltyPoints = user.getPenaltyPoints();
                user.setRentBan(LocalDate.now().plusMonths(calculateRentBan(penaltyPoints)));
            }
            user.removeBookFromListOfRentedBooks(book);
            book.setAvailable(true);
            return true;
        }
        return false;
    }

    public boolean rentBook(Book book, User user) {
        if (book.isAvailable() && isPenalty(user) == false) {
            user.addRentedBookToListOfRentedBooks(book);
            book.setAvailable(false);
            book.setDeadlineDate(LocalDate.now().plusDays(14));
            return true;
        }
        return false;
    }

    private int calculateRentBan(int penaltyPoints) {
        int montsOfBan;
        if (penaltyPoints > 10) {
            montsOfBan = penaltyPoints / 10;
            return montsOfBan;
        }
        return 0;
    }

    public boolean isPenalty(User user) {
        if (LocalDate.now().isAfter(user.getRentBan())) {
            return false;
        }
        return true;
    }


}
