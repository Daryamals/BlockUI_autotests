package com.way2automation.tests.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PaymentPage extends BasePage {
    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement submitButton;
    @FindBy(tagName = "h3")
    private WebElement finalMessage;
    @FindBy(xpath = "//a[contains(., 'Payment')]")
    private WebElement paymentStep;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public void handleSuccessAlert() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
            System.out.println("Alert not found, proceeding...");
        }
    }

    public String getFinalMessage() {
        return wait.until(ExpectedConditions.visibilityOf(finalMessage)).getText();
    }

    public boolean isPaymentStepActive() {
        return wait.until(ExpectedConditions.visibilityOf(paymentStep))
                .getAttribute("class").contains("active");
    }
}