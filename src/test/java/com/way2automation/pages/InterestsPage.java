package com.way2automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.way2automation.config.TestConfig;

public class InterestsPage extends BasePage {
    @FindBy(css = "input[value='xbox']")
    private WebElement xboxRadioButton;
    @FindBy(xpath = "//a[contains(text(),'Next Section')]")
    private WebElement nextSectionButton;
    @FindBy(xpath = "//a[contains(., 'Interests')]")
    private WebElement interestsStep;

    public InterestsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Выбор интереса")
    public InterestsPage selectXboxInterest() {
        wait.until(ExpectedConditions.elementToBeClickable(xboxRadioButton)).click();
        return this;
    }

    @Step("Нажатие на кнопку 'Next Section'")
    public PaymentPage clickNextSection() {
        wait.until(ExpectedConditions.elementToBeClickable(nextSectionButton)).click();
        waitForUrlToContain(TestConfig.getPaymentUrlPath());
        return new PaymentPage(driver);
    }

    @Step("Проверка, что шаг 'Interests' активен")
    public boolean isInterestsStepActive() {
        return wait.until(ExpectedConditions.visibilityOf(interestsStep))
                .getAttribute("class").contains("active");
    }
}