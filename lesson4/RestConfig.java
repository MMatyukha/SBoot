package ru.gb.test.qa.Test_auto.lesson4;

import org.springframework.context.annotation.Bean;

import java.net.http.HttpClient;

public class RestConfig {
    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }
}
