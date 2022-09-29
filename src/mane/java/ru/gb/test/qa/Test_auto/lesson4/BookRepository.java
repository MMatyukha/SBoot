package ru.gb.test.qa.Test_auto.lesson4;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    Optional<BookEntity>findByNameAndid(String Name, int secondName);
}



