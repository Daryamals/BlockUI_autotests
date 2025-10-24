package com.way2automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlExPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(SqlExPage.class);
    @FindBy(name = "login")
    private WebElement loginInput;
    @FindBy(name = "psw")
    private WebElement passwordInput;
    @FindBy(css = "input[name='subm1']")
    private WebElement loginButton;
    @FindBy(xpath = "//a[contains(@href, 'logout')]")
    private WebElement logoutLink;
    @FindBy(xpath = "//button[contains(text(), 'Согласен')]")
    private WebElement acceptCookieButton;

    public SqlExPage(WebDriver driver) {
        super(driver);
    }

    @Step("Авторизация на sql-ex.ru с логином {username} и паролем")
    public SqlExPage login(String username, String password) {
        try {
            if (waitHelper.waitForVisibilityOf(acceptCookieButton).isDisplayed()) {
                acceptCookieButton.click();
            }
        } catch (Exception e) {
            logger.info("Баннер для принятия Cookie не найден, продолжаем...");
        }
        waitHelper.waitForVisibilityOf(loginInput).sendKeys(username);
        waitHelper.waitForVisibilityOf(passwordInput).sendKeys(password);
        waitHelper.waitForElementToBeClickable(loginButton).click();
        return new SqlExPage(driver);
    }

    @Step("Проверка, что пользователь авторизован")
    public boolean isUserLoggedIn() {
        try {
            return waitHelper.waitForVisibilityOf(logoutLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}