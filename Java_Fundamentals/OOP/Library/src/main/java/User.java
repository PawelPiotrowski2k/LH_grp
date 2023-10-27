import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class User {
    private final String name;
    private final String surName;
    private final String email;
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

    public void setPenaltyPoints(int penaltyPoints) {
        this.penaltyPoints = penaltyPoints;
    }

    public void setRentBan(LocalDate rentBan) {
        this.rentBan = rentBan;
    }
    public void addRentedBookToListOfRentedBooks(Book book){
        listOfRentedBooks.add(book);
    }

    public int getPenaltyPoints() {
        return penaltyPoints;
    }

    public LocalDate getRentBan() {
        return rentBan;
    }

    public List<Book> getListOfRentedBooks() {
        return listOfRentedBooks;
    }
    public void removeBookFromListOfRentedBooks(Book book){
        listOfRentedBooks.remove(book);
    }

}
