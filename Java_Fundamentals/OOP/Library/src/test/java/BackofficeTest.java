import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BackofficeTest {
    Backoffice backoffice;
    Book book;
    User user;
    User secondUser;

    @BeforeEach
    void setup (){
        book = new Book("Harry Potter", "J.K Rowling");
        user = new User("pawel","piotrowski","piotrowski@wp.pl");
        backoffice = new Backoffice();

    }




    @Test
    @DisplayName("simple return book")
    void returnBook() {
        backoffice.addUser(user);
        backoffice.addBook(book);
        backoffice.rentBook(book,user);
        assertTrue(backoffice.returnBook(book,user));
        assertTrue(user.isSuspended == false);
    }


    @Test
    void rentBook() {
        backoffice.addBook(book);
        backoffice.addUser(user);
        assertTrue(backoffice.rentBook(book,user));
    }
    @Test
    @DisplayName("try to rent book that is already rented")
    void advancedrentBook() {
        backoffice.addBook(book);
        backoffice.addUser(user);
        secondUser = new User("Radek", "Kotarski","kotarski@wp.pl");
        backoffice.addUser(secondUser);
        backoffice.rentBook(book,user);
        assertFalse(backoffice.rentBook(book,secondUser));
    }
}