package ru.zdoher.library.model;

public class Book extends BaseEntity {
    private String authorName;
    private String genre;

    public Book(int id, String name, String authorName, String genre) {
        super(id, name);

        this.authorName = authorName;
        this.genre = genre;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Book{" +
                "authorName='" + authorName + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
