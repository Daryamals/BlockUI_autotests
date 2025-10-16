package com.way2automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "formly_1_input_username_0")
    private WebElement usernameRequiredInput;

    @FindBy(css = ".alert-danger")
    private WebElement errorMessage;

    @FindBy(css = "button.btn-danger")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод имени пользователя в первое поле: {username}")
    public LoginPage fillUsername(String username) {
        waitHelper.waitForVisibilityOf(usernameInput).sendKeys(username);
        return this;
    }

    @Step("Ввод пароля: {password}")
    public LoginPage fillPassword(String password) {
        waitHelper.waitForVisibilityOf(passwordInput).sendKeys(password);
        return this;
    }

    @Step("Ввод имени пользователя в обязательное поле 'Username *': {username}")
    public LoginPage fillRequiredUsername(String username) {
        waitHelper.waitForVisibilityOf(usernameRequiredInput).sendKeys(username);
        return this;
    }

    @Step("Выполнение входа с именем пользователя '{username}' и паролем '{password}'")
    public LoginPage performLogin(String username, String password) {
        return fillUsername(username)
                .fillPassword(password)
                .fillRequiredUsername(username)
                .clickLogin();
    }

    @Step("Выполнение частичного заполнения для проверки состояния кнопки")
    public void fillLoginForm(String username, String password) {
        if (username != null && !username.isEmpty()) {
            fillUsername(username);
        }
        if (password != null && !password.isEmpty()) {
            fillPassword(password);
        }
    }

    @Step("Нажатие на кнопку 'Login'")
    public LoginPage clickLogin() {
        waitHelper.waitForElementToBeClickable(loginButton).click();
        return this;
    }

    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        return waitHelper.waitForVisibilityOf(errorMessage).getText();
    }

    @Step("Проверка, что кнопка 'Login' активна")
    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }
}