public class BookBuilder {
    private String title;
    private String author;

    public BookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }
    public Book build(){
        return new Book(title,author);
    }
}
