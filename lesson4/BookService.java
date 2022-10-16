package ru.gb.test.qa.Test_auto.lesson4;

import ru.gb.test.qa.Test_auto.Book;

public interface BookService {
    void save(Book bookDto);

    void update(Book bookDto);

    Book getById(Integer id);

    void delete(Integer id);

}
