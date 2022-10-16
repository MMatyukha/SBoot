package ru.gb.test.qa.Test_auto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.gb.test.qa.Test_auto.lesson7.GeekBrainsMainPage;
import ru.gb.test.qa.Test_auto.lesson7.JavaProfessionPage;
import ru.gb.test.qa.Test_auto.lesson7.JavaMobilePage;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = GeekBrainsTestConfig.class)
@TestPropertySource(locations = "/application.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GeekBrainsSearchInfoTest {
    @Autowired
    private GeekBrainsMainPage mainPage;

    @Autowired
    private JavaMobilePage javaMobilePage;

    @Autowired
    private JavaProfessionPage javaProfessionPage;

    @Test
    public void getJavaMobilePageTest() {

        mainPage.getMainPage()
                .search("Java")
                .getProfession("Мобильные приложения на Java");

        Assertions.assertThat(javaMobilePage.getFormOffer().getText())
                .containsIgnoringCase("Создай с нуля интересную викторину и разработай своё приложение для android-смартфона");

    }

    @Test
    public void getJavaProfessionTest() {

        mainPage.getMainPage()
                .search("Java")
                .getProfession("Программист Java");

        Assertions.assertThat(javaProfessionPage.getFormOffer().getText())
                .containsIgnoringCase("Пройдите обучение на инженера-программиста на Java.");

    }

}
