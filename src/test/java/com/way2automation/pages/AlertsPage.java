package com.way2automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertsPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(AlertsPage.class);

    @FindBy(xpath = "//a[normalize-space()='Simple Alert']")
    private WebElement simpleAlertTab;

    @FindBy(xpath = "//a[normalize-space()='Input Alert']")
    private WebElement inputAlertTab;

    private final By iframeLocator = By.xpath("//div[@id='example-1-tab-2']//iframe");

    @FindBy(xpath = "//button[contains(text(),'demonstrate the Input box')]")
    private WebElement triggerAlertButton;

    @FindBy(id = "demo")
    private WebElement resultText;

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Вызвать 'Input Alert'")
    public AlertsPage triggerInputAlert() {
        waitHelper.waitForElementToBeClickable(inputAlertTab).click();
        WebElement iframeElement = waitHelper.waitForElementPresence(iframeLocator);
        waitHelper.waitForFrameToBeAvailableAndSwitchToIt(iframeElement);
        waitHelper.waitForElementToBeClickable(triggerAlertButton).click();
        return this;
    }

    @Step("Ввести текст '{textToEnter}' в алерт и принять его")
    public AlertsPage handleAlert(String textToEnter) {
        logger.info("Обрабатываем алерт с текстом: {}", textToEnter);
        Alert alert = waitHelper.waitForAlertToBePresent();
        alert.sendKeys(textToEnter);
        alert.accept();
        return this;
    }

    private void stabilizePageAfterAlert() {
        driver.switchTo().defaultContent();
        waitHelper.waitForElementToBeClickable(simpleAlertTab).click();
        waitHelper.waitForElementToBeClickable(inputAlertTab).click();
    }

    @Step("Получить итоговый текст после обновления страницы")
    public String getResultText(String expectedText) {
        stabilizePageAfterAlert();
        WebElement iframeElement = waitHelper.waitForElementPresence(iframeLocator);
        waitHelper.waitForFrameToBeAvailableAndSwitchToIt(iframeElement);
        waitHelper.waitForTextToBePresentInElement(resultText, expectedText);
        String text = resultText.getText();
        driver.switchTo().defaultContent();
        return text;
    }
}