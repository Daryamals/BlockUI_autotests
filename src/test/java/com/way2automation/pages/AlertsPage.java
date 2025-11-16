package com.way2automation.pages;

import com.way2automation.config.TestConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AlertsPage extends BasePage {

    @FindBy(xpath = "//a[normalize-space()='Input Alert']")
    private WebElement inputAlertTab;

    @FindBy(xpath = "//div[@id='example-1-tab-2']//iframe")
    private WebElement inputAlertFrame;

    @FindBy(xpath = "//button[contains(text(),'demonstrate the Input box')]")
    private WebElement triggerAlertButton;

    @FindBy(id = "demo")
    private WebElement resultText;

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу Alerts")
    public AlertsPage open() {
        driver.get(TestConfig.getAlertsUrl());
        return this;
    }

    @Step("Вызвать 'Input Alert', ввести текст '{textToEnter}' и принять его")
    public AlertsPage triggerAndHandleInputAlert(String textToEnter) {
        waitHelper.waitForElementToBeClickable(inputAlertTab).click();
        driver.switchTo().frame(inputAlertFrame);
        waitHelper.waitForElementToBeClickable(triggerAlertButton).click();
        Alert alert = waitHelper.waitForAlertToBePresent();
        alert.sendKeys(textToEnter);
        alert.accept();
        driver.switchTo().defaultContent();
        return this;
    }

    @Step("Проверить, что итоговый текст соответствует '{expectedText}'")
    public AlertsPage verifyResultTextIs(String expectedText) {
        driver.switchTo().frame(inputAlertFrame);
        waitHelper.waitForTextToBePresentInElement(resultText, expectedText);
        String actualText = resultText.getText();
        Assert.assertEquals(actualText, expectedText, "Итоговый текст не соответствует ожидаемому.");
        driver.switchTo().defaultContent();
        return this;
    }
}