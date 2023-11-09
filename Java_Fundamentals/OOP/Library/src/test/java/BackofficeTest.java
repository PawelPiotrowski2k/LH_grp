import Models.Book;
import Models.User;
import Operations.PenaltyManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class BackofficeTest {
    PenaltyManager penaltyManager;
    Backoffice backoffice;
    Book book;
    User user;
    User secondUser;

    @BeforeEach
    void setup (){
        book = new Book("Harry Potter", "J.K Rowling");
        user = new User("pawel","piotrowski","piotrowski@wp.pl",false);
        backoffice = new Backoffice();
    }




    @Test
    @DisplayName("simple return book")
    void returnBook() {
        backoffice.addUser(user);
        backoffice.addBook(book);
        backoffice.rentBook(book,user);

    }


    @Test
    void rentBook() {
        backoffice.addBook(book);
        backoffice.addUser(user);
    }
    @Test
    @DisplayName("try to rent book that is already rented")
    void advancedrentBook() {
        backoffice.addBook(book);
        backoffice.addUser(user);
        secondUser = new User("Radek", "Kotarski","kotarski@wp.pl",false);
        backoffice.addUser(secondUser);
        backoffice.rentBook(book,user);
    }
}