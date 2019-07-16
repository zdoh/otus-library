package ru.zdoher.library.controller;

public interface BookController {

    void addBook();

    void addCommentToBook(String id);

    void deleteCommentFromBook(String id);

    void showAll();

    void getById(String id);

    void delete(String id);

}
