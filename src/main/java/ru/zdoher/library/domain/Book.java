package ru.zdoher.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Book {

    @Id
    private String id;

    private String name;

    @DBRef
    private Author author;

    @DBRef
    private Genre genre;

    @DBRef
    private List<Comment> comments = new ArrayList<>();

    public Book(String name, Author author, Genre genre, Comment... comments) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.comments = Arrays.asList(comments);
    }
}
