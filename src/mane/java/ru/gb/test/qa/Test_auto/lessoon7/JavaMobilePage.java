package ru.gb.test.qa.Test_auto.lesson7;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class JavaMobilePage {
    private final WebDriver driver;

    private List<WebElement> formOffer;

    public JavaMobilePage(@Lazy WebDriver driver, List<WebElement> formOffer) {
        this.driver = driver;
        this.formOffer = formOffer;
        PageFactory.initElements(driver, this);
    }

    public WebElement getFormOffer() {
        return formOffer.get(0);
    }
}
