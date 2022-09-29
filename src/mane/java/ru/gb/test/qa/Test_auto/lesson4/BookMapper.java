package ru.gb.test.qa.Test_auto.lesson4;

import ru.gb.test.qa.Test_auto.Book;

public interface BookMapper {

    BookMapper dtoToEntity(Book bookDto);

    Book entityToDto(BookEntity userEntity);
}
