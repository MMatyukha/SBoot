package ru.gb.test.qa.Test_auto.lesson4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.test.qa.Test_auto.Book;
import ru.gb.test.qa.Test_auto.lesson4.BookMapper;
import ru.gb.test.qa.Test_auto.lesson4.BookRepository;
import ru.gb.test.qa.Test_auto.lesson4.BookEntity;

@Service
@RequiredArgsConstructor
public class BookServiceLmpl implements BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;



    @Override
    @Transactional
    public void save(Book bookDto) {
        BookEntity entity = (BookEntity) bookMapper.dtoToEntity(bookDto);
        bookRepository.save(entity);
    }

    @Override
    @Transactional
    public void update(Book bookDto) {
        BookEntity entity = bookRepository.findById(bookDto.getId())
                .orElseThrow();
        entity.setName(bookDto.getName());

    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(Integer id) {
        BookEntity entity = bookRepository.findById(id)
                .orElseThrow();
        return bookMapper.entityToDto(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

}
