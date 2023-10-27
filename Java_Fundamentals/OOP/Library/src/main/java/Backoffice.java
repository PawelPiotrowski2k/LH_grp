public class Backoffice {

    public static Backoffice backoffice = new Backoffice();

    public Book addBook(String title, String author) {
        Book book;
        book = new BookBuilder().setTitle(title).setAuthor(author).build();
        return book;
    }

    public void removeBook(Book book) {
        book = null;
    }

    public User addUser(String name, String surName, String email) {
        User user = new User(name, surName, email);
        return user;
    }

    public void removeUser(User user) {
        user = null;
    }
}
