import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Backoffice {
    private final List<Book> books = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    PenaltyManager penaltyManager = new PenaltyManager();


    public void addBook(Book book) {
        books.add(book);
        penaltyManager.setDeadLineDate(book, LocalDate.now());
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void addUser(User user) {
        users.add(user);
        penaltyManager.resetPenaltyPoints(user);
        penaltyManager.setBanDate(LocalDate.now(), user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public boolean returnBook(Book book, User user) {
        if (user.getListOfRentedBooks().contains(book)) {
            if (LocalDate.now().isAfter(penaltyManager.getDeadLineDate(book))) {
                penaltyManager.increasePenaltyPoints(user, (int) ChronoUnit.DAYS.between(LocalDate.now(), penaltyManager.getDeadLineDate(book)));
                int penaltyPoints = penaltyManager.getPenaltyPoints(user);
                if (penaltyPoints > 10) {
                    penaltyManager.setBanDate(LocalDate.now().plusMonths(calculateRentBan(penaltyPoints)), user);
                    user.setSuspended(true);
                    penaltyManager.resetPenaltyPoints(user);
                }
            }
            user.removeBookFromListOfRentedBooks(book);
            book.setAvailable(true);
            return true;
        }
        return false;
    }

    public boolean rentBook(Book book, User user) {
        if (book.isAvailable() && !isPenalty(user)) {
            user.addRentedBookToListOfRentedBooks(book);
            book.setAvailable(false);
            penaltyManager.setDeadLineDate(book, LocalDate.now().plusDays(14));
            user.isSuspended = false;
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
        if (penaltyManager.getBanDate(user).isAfter(LocalDate.now()) || !penaltyManager.getBanDate(user).equals(LocalDate.now())) {
            return true;
        }
        return false;
    }
}
