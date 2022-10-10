package ru.gb.test.qa.Test_auto.lesson7;

import lombok.SneakyThrows;

public class Pause {
    @SneakyThrows
    public static void pause(Integer seconds) {
        Thread.sleep(seconds * 1000);
    }
}
