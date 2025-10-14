package com.way2automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {
    @FindBy(name = "name")
    private WebElement nameInput;
    @FindBy(name = "email")
    private WebElement emailInput;
    @FindBy(css = ".btn.btn-block.btn-info")
    private WebElement nextSectionButton;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage fillName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
        return this;
    }

    public ProfilePage fillEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    public ProfilePage fillProfileForm(String name, String email) {
        fillName(name);
        fillEmail(email);
        return this;
    }

    public InterestsPage clickNextSection() {
        wait.until(ExpectedConditions.elementToBeClickable(nextSectionButton)).click();
        waitForUrlToContain("/form/interests");
        return new InterestsPage(driver);
    }
}