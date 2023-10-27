import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BackofficeTest {
    Book book;
    User objectUnderTest;


    User createFirstUser() {
        return new User("Pawel", "Piotrowski", "piotrowski@wp.pl");
    }

    public Book createBook() {
        Book book = new BookBuilder().setTitle("Harry Potter").setAuthor("J.K. Rowling").build();
        return book;
    }

    @BeforeEach
    void setup() {
        objectUnderTest = new User("Konrad", "Dudek", "dudek@wp.pl");
        book = new BookBuilder().setAuthor("J.K. Rowling").setTitle("Harry Potter").build();
    }

    @Test
    void returnBook() {
        //Given book, user
        //When
        Backoffice.backoffice.rentBook(book,objectUnderTest);
        Backoffice.backoffice.returnBook(book,objectUnderTest);
        //Then
        assertFalse(objectUnderTest.getListOfRentedBooks().contains(book));
    }

    @Test
    void rentBook() {
        //Given book, user

        //when
        Backoffice.backoffice.rentBook(book,objectUnderTest);
        //then
        assertTrue(objectUnderTest.getListOfRentedBooks().contains(book));


    }
}