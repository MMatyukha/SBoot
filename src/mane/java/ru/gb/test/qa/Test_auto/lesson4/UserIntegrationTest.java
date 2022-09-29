package ru.gb.test.qa.Test_auto.lesson4;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.annotation.DirtiesContext;
import ru.gb.test.qa.Test_auto.Book;
import ru.gb.test.qa.Test_auto.lesson4.BookEntity;
import ru.gb.test.qa.Test_auto.lesson4.BookRepository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static javax.servlet.http.HttpServletResponse.SC_OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserIntegrationTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private BookRepository bookRepository;

    @LocalServerPort
    private int port;

    public UserIntegrationTest(ObjectMapper objectMapper, BookRepository bookRepository) {
        this.objectMapper = objectMapper;
        this.bookRepository = bookRepository;
    }

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void saveUserTest() throws Exception {
        Book book =new Book();
        book.setId(4);
        book.setName("Name");

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(getRootUrl() + "/user-rest"))
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(book)))
                .build();

        //step 1
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        //intermediate assert after first step
        Assertions.assertThat(response.statusCode()).isEqualTo(SC_OK);

        //step 2
        BookEntity bookEntity = bookRepository.findAll().stream()
                .findFirst()
                .orElseThrow();

        //assert
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(bookEntity.getName()).isEqualTo(book.getName());
            s.assertThat(bookEntity.getID()).isEqualTo(book.getID());
        });

    }
    @Test
    void getUserTest() throws Exception {
        //pre-condition
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(" Name");
        bookEntity.setID(4);
        BookEntity entity = bookRepository.saveAndFlush(bookEntity);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(getRootUrl() + "/user-rest/" + entity.getId()))
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .GET()
                .build();

        //step 1
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        //intermediate assert after first step
        Assertions.assertThat(response.statusCode()).isEqualTo(SC_OK);

        //assert
        Book book = objectMapper.readValue(response.body(), User.class);
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(bookEntity.getName()).isEqualTo(book.getName());
            s.assertThat(bookEntity.getID()).isEqualTo(book.getID());

        });
    }

    @Test
    @Disabled
    void updateUserTest() {
        //TODO Implement update user test
    }

    @Test
    @Disabled
    void deleteUserTest() {
        //TODO Implement delete user test
    }
}


}

