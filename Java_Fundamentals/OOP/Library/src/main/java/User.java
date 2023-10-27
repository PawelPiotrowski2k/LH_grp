import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class User {
    private final String name;
    private final String surName;
    private String email;
    private int penaltyPoints;
    private LocalDate rentBan;
    private final List<Book> listOfRentedBooks = new ArrayList<>();

    public User(String name, String surName, String email) {
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.rentBan = LocalDate.now().minusDays(1);
        this.penaltyPoints = 0;
    }


    public List<Book> getListOfRentedBooks() {
        return listOfRentedBooks;
    }

    public boolean rentBook(Book book) {
        if (book.isAvailable() && isPenalty() == false) {
            listOfRentedBooks.add(book);
            book.setAvailable(false);
            book.setDeadlineDate(LocalDate.now().plusDays(14));
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book) {
        if(listOfRentedBooks.contains(book)) {
            if (LocalDate.now().isAfter(book.getDeadlineDate())) {
                penaltyPoints = (int) ChronoUnit.DAYS.between(LocalDate.now(), book.getDeadlineDate());
                calculateRentBan(penaltyPoints);

            }
            listOfRentedBooks.remove(book);
            book.setAvailable(true);
            return true;
        }
        return false;
    }

    private boolean isPenalty() {
        return rentBan.isAfter(LocalDate.now());
    }

    private void calculateRentBan(int penaltyPoints) {
        int montsOfBan;
        if (penaltyPoints > 10) {
            montsOfBan = penaltyPoints / 10;
            rentBan = LocalDate.now().plusMonths(montsOfBan);
        }
    }


}
