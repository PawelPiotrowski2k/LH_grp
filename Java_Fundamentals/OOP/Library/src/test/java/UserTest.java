import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {


    Book book;
    User objectUnderTest;
    User createFirstUser(){
        return new User("Pawel","Piotrowski","piotrowski@wp.pl");
    }
    public Book createBook(){
        Book book = new BookBuilder().setTitle("Harry Potter").setAuthor("J.K. Rowling").build();
        return book;
    }
    @BeforeEach
    void setup(){
        objectUnderTest = new User("Konrad", "Dudek","dudek@wp.pl");
        book = new BookBuilder().setAuthor("J.K. Rowling").setTitle("Harry Potter").build();
    }


    @Test
    void rentBook() {
        //Given
        Book book = createBook();
        //when
        objectUnderTest.rentBook(book);
        //Then
        assertTrue(!book.isAvailable());

    }
    @Test
    void addBookThatIsAlreadyTaken(){
        //Given
        User firstUser = createFirstUser();
        //book and object under test
        //When
        firstUser.rentBook(book);
        objectUnderTest.returnBook(book);
        //Then
        assertFalse(objectUnderTest.rentBook(book));
    }

    @Test
    void returnBook() {
        //Given
        Book book = createBook();
        //when
        objectUnderTest.rentBook(book);
        objectUnderTest.returnBook(book);
        //Then
        assertFalse(objectUnderTest.getListOfRentedBooks().contains(book));
    }
}