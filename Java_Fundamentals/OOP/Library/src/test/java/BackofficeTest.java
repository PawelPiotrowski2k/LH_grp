import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BackofficeTest {
    User objectUnderTest;
    Book book;

    User createFirstUser(){
        return new User("Pawel","Piotrowski","piotrowski@wp.pl");
    }


    @BeforeEach
    void setup(){
        objectUnderTest = new User("Konrad","Dudek","dudek@wp.pl");
        book = new BookBuilder().setTitle("Harry Potter").setAuthor("J.K. Rowling").build();
    }



    @Test
    void addBook(){
        //Given
        User objectUnderTest = createFirstUser();
        //book and object under test
        //When
        objectUnderTest.rentBook(book);
        List<Book> listOfUsersRentedBooks = new ArrayList<Book>(objectUnderTest.getListOfRentedBooks());
        //Then
        assertTrue(listOfUsersRentedBooks.contains(book));
    }

}