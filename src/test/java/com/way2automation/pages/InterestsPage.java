package com.way2automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public void selectXboxInterest() {
        wait.until(ExpectedConditions.elementToBeClickable(xboxRadioButton)).click();
    }

    public PaymentPage clickNextSection() {
        wait.until(ExpectedConditions.elementToBeClickable(nextSectionButton)).click();
        waitForUrlToContain("/payment");
        return new PaymentPage(driver);
    }

    public boolean isInterestsStepActive() {
        return wait.until(ExpectedConditions.visibilityOf(interestsStep))
                .getAttribute("class").contains("active");
    }
}