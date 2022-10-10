package ru.gb.test.qa.Test_auto.lesson7;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.gb.test.qa.Test_auto.lesson7.Pause;

@Component
public class SearchResultPage {

    private final WebDriver driver;

    @Value("${element.explicit.wait}")
    private Long explicitWait;

    private List<WebElement> searchPageHeader;

    private WebElement profession;

    private List<WebElement> professionTitles;

    public SearchResultPage(@Lazy WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void getProfession(String name) {

        WebElement waitedProfession = new WebDriverWait(driver, Duration.ofSeconds(explicitWait))
                .until(ExpectedConditions.elementToBeClickable(profession));

        //element not click after waiting
        Pause.pause(1);
        waitedProfession.click();

        List<WebElement> waitProfessionTitles = new WebDriverWait(driver, Duration.ofSeconds(explicitWait))
                .until(ExpectedConditions.visibilityOfAllElements(professionTitles));

        //element not click after waiting
        Pause.pause(1);
        waitProfessionTitles.stream()
                .filter(w -> w.getText().contains(name))
                .findFirst()
                .orElseThrow()
                .click();
    }
}
