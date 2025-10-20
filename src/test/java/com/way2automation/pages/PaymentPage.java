package com.way2automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(PaymentPage.class);

    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement submitButton;
    @FindBy(tagName = "h3")
    private WebElement finalMessage;
    @FindBy(xpath = "//a[contains(., 'Payment')]")
    private WebElement paymentStep;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажатие на кнопку 'Submit'")
    public PaymentPage clickSubmit() {
        waitHelper.waitForElementToBeClickable(submitButton).click();
        return this;
    }

    @Step("Подтверждение alert-сообщения об успехе")
    public PaymentPage handleSuccessAlert() {
        Alert alert = waitHelper.waitForAlertToBePresent();
        alert.accept();
        return this;
    }

    @Step("Получение финального сообщения")
    public String getFinalMessage() {
        return waitHelper.waitForVisibilityOf(finalMessage).getText();
    }

    @Step("Проверка, что шаг 'Payment' активен")
    public boolean isPaymentStepActive() {
        return waitHelper.waitForVisibilityOf(paymentStep)
                .getAttribute("class").contains("active");
    }
}