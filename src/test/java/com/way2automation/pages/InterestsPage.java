package com.way2automation.pages;

import com.way2automation.config.TestConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        waitHelper.waitForElementToBeClickable(xboxRadioButton).click();
        return this;
    }

    @Step("Нажатие на кнопку 'Next Section'")
    public PaymentPage clickNextSection() {
        waitHelper.waitForElementToBeClickable(nextSectionButton).click();
        waitForUrlToContain(TestConfig.getPaymentUrlPath());
        return new PaymentPage(driver);
    }

    @Step("Проверка, что шаг 'Interests' активен")
    public boolean isInterestsStepActive() {
        return waitHelper.waitForVisibilityOf(interestsStep)
                .getAttribute("class").contains("active");
    }
}