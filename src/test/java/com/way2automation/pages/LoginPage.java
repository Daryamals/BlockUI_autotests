package com.way2automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        wait.until(ExpectedConditions.visibilityOf(usernameInput)).sendKeys(username);
        return this;
    }

    @Step("Ввод пароля: {password}")
    public LoginPage fillPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
        return this;
    }

    @Step("Ввод имени пользователя в обязательное поле 'Username *': {username}")
    public LoginPage fillRequiredUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameRequiredInput)).sendKeys(username);
        return this;
    }

    @Step("Выполнение входа с именем пользователя '{username}' и паролем '{password}'")
    public void performLogin(String username, String password) {
        fillUsername(username)
                .fillPassword(password)
                .fillRequiredUsername(username);
        clickLogin();
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
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }

    @Step("Проверка, что кнопка 'Login' активна")
    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }
}