package ru.gb.test.qa.Test_auto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BookControllerTest {
    Map<Integer, java.awt.print.Book> data =new HashMap<>();


    @PostConstruct
    void init(){
        data.put(1, new java.awt.print.Book( id: 1, name: "Domovodstvo"));
        data.put(1, new java.awt.print.Book( id: 2, name: "Sadovodstvo"));
    }
    @GetMapping("/{id}")
    public Book (@PathVariable int id) {
        return data.get(id);
    }

    @GetMapping("/all")
    public List<java.awt.print.Book> getAllBooks() {
        return new ArrayList<>(data.values());
    }

    @PostMapping()
    public void saveBook(@RequestBody Book book) {
        int id = data.size() + 1;
        book.setID(id);
        data.put(id, book);
    }
}


