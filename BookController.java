package ru.gb.test.qa.Test_auto;

import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")

public class BookController {
    Map<Integer, Book > data =new HashMap<>();

     private int id;
    private  String name;


    @PostConstruct
    void init(){
        data.put(1, new Book(id: 1, name: "Domovodstvo"));
        data.put(2, new Book(id: 2, name: "Sadovodstvo"));
        data.put(3, new Book(id: 3, name: "Kulinaria"));
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable int id) {
        return data.get(id);
    }

    @GetMapping("/all")
    public List<Book> get() {
        return new ArrayList<>(data.values());
    }

    @PostMapping()
    public void save(@RequestBody Book book) {
        int id = data.size() + 1;
        book.setID(id);
        data.put(id, book);
    }


    @PutMapping("/{id}")
    public void change(@PathVariable int id, @RequestBody Book bookChanding){
            Book  book= data.get(id);
            book.setName(bookChanding.getName());
            data.put(id,book);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        data.remove(id);
    }
}
