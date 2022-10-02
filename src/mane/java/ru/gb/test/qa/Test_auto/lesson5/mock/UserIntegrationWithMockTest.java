package ru.gb.test.qa.Test_auto.lesson5.mock;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import ru.gb.test.qa.Test_auto.Book;
import ru.gb.test.qa.Test_auto.lesson4.BookService;
import ru.gb.test.qa.Test_auto.lesson4.DirtiesContext;
import ru.gb.test.qa.Test_auto.lesson4.SpringBootTest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.springframework.http.RequestEntity.delete;
import static org.springframework.web.servlet.function.ServerResponse.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class UserIntegrationWithMockTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HttpClient httpClient;

    @MockBean
    private BookService bookService;

    private Book book;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }
    @BeforeAll
    void init() {
        book = new Book();
        book.setId(4);
        book.setName("Name");
        Mockito.when(bookService.getById(Mockito.anyInt())).thenReturn(book);
    }

    @Test
    void getBookTest() throws Exception {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(getRootUrl() + "/book-rest/" + new Random().nextInt(5)))
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .GET()
                .build();

        //step 1
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        //intermediate assert after first step
        Assertions.assertThat(response.statusCode()).isEqualTo(SC_OK);

        //assert
        Book bookResponse = objectMapper.readValue(response.body(), Book.class);
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(book.getName()).isEqualTo(bookResponse.getName());
            s.assertThat(book.getId()).isEqualTo(bookResponse.getID());
        });
    }

    @Test
    public void deleteBook() throws Exception {
     init()
                .perform(delete("/books/4"))
                .andExpect(status().isOk());
        verify(bookService, times(1)).deleteBook(anyLong());
    }

}
