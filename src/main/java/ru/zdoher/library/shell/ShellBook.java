package ru.zdoher.library.shell;


import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.zdoher.library.dao.BookDao;

@ShellComponent
public class ShellBook {
    private BookDao bookDao;

    public ShellBook(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @ShellMethod("asd")
    public void bookAllShow() {
        bookDao.getAll().forEach(System.out::println);
    }
}
