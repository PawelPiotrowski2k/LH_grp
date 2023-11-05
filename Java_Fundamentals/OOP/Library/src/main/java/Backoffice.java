import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Backoffice {
    private final List<Book> books;
    private final List<User> users;
    PenaltyManager penaltyManager;
    private final Map<Book,User> rentedBooks;
    private final Map<Book,LocalDate> bookDeadline;
   public Backoffice(){
       penaltyManager = new PenaltyManager();
       books = new ArrayList<Book>();
       users = new ArrayList<User>();
       rentedBooks = new HashMap<>();
       bookDeadline = new HashMap<>();
   }


    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public boolean returnBook(Book book, User user) {
        if (rentedBooks.containsKey(book) && rentedBooks.get(book).equals(user)) {
            if (LocalDate.now().isAfter(bookDeadline.get(book))) {
                penaltyManager.increasePenaltyPoints(user, (int) ChronoUnit.DAYS.between(LocalDate.now(), bookDeadline.get(book)));
                int penaltyPoints = penaltyManager.getPenaltyPoints(user);
                if (penaltyPoints > 10) {
                    penaltyManager.setBanDate(LocalDate.now().plusMonths(calculateRentBan(penaltyPoints)), user);
                    user.setSuspended(true);
                    penaltyManager.resetPenaltyPoints(user);
                }
            }
            rentedBooks.remove(book);
            bookDeadline.remove(book);
            book.setAvailable(true);
            return true;
        }
        return false;
    }

    public boolean rentBook(Book book, User user) {
        if (book.isAvailable() && !isPenalty(user)) {
            rentedBooks.put(book,user);
            book.setAvailable(false);
            if(!bookDeadline.containsKey(book)){
                bookDeadline.put(book,LocalDate.now().plusDays(14));
            }
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
       if(penaltyManager.getBanDate().containsKey(user)){
           if (penaltyManager.getBanDate().get(user).isAfter(LocalDate.now()) || !penaltyManager.getBanDate().get(user).equals(LocalDate.now())) {
               return true;
           }
       }

        return false;
    }
}
