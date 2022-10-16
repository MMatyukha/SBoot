package ru.gb.test.qa.Test_auto.lesson4;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.test.qa.Test_auto.Book;

@RestController
@RequestMapping("/book-rest")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    void save(@RequestBody Book book) {
        bookService.save(book);
    }

    @PutMapping
    void update(@RequestBody Book book) {
        bookService.update(book);
    }

    @GetMapping("/{id}")
    Book getUserById(@PathVariable Integer id) {
        return bookService.getById(id);
    }

    @DeleteMapping("/{id}")
    void deleteUserById(@PathVariable Integer id) {
        bookService.delete(id);
    }
}


