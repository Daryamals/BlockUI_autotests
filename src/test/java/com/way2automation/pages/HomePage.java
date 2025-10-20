package com.way2automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutLink;

    @FindBy(tagName = "h1")
    private WebElement header;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверка, что пользователь вошел в систему")
    public boolean isUserLoggedIn() {
        try {
            waitHelper.waitForVisibilityOf(logoutLink);
            return logoutLink.isDisplayed() && header.getText().equals("Home");
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Выход из системы")
    public LoginPage logout() {
        waitHelper.waitForElementToBeClickable(logoutLink).click();
        return new LoginPage(driver);
    }
}