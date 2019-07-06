package ru.zdoher.library.controller;

public interface BookController {

    void addBook();

    void showAll();

    void getById(String id);

    void delete(String id);

}
