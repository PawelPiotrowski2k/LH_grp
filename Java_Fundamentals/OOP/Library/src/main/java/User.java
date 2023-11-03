import java.util.ArrayList;
import java.util.List;


public class User {
    private final String name;
    private final String surName;
    private final String email;
    boolean isSuspended;
    private final List<Book> listOfRentedBooks = new ArrayList<>();

    public User(String name, String surName, String email) {
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.isSuspended = false;
    }

    public List<Book> getListOfRentedBooks() {
        return listOfRentedBooks;
    }
    public void setSuspended(boolean suspended){
        isSuspended = suspended;
    }

    public void removeBookFromListOfRentedBooks(Book book){
        listOfRentedBooks.remove(book);
    }

    public void addRentedBookToListOfRentedBooks(Book book){
        listOfRentedBooks.add(book);
    }
}
