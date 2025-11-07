package com.way2automation.tests;

import com.way2automation.config.TestConfig;
import com.way2automation.data.LoginProvider;
import com.way2automation.pages.HomePage;
import com.way2automation.pages.LoginPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

@Epic("Авторизация")
@Feature("Вход в систему")
public class LoginPageTests extends BaseTest {

    private LoginPage loginPage;

    @Parameters({"browser", "grid"})
    @BeforeMethod
    public void setUp(String browser, String isGrid) throws MalformedURLException {
        super.setUp(browser, isGrid);
        getDriver().get(TestConfig.getLoginUrl());
        HomePage homePage = new HomePage(getDriver());
        if (homePage.isUserLoggedIn()) {
            homePage.logout();
        }
        loginPage = new LoginPage(getDriver());
    }

    @Test(dataProvider = "positiveLoginData", dataProviderClass = LoginProvider.class, description = "Успешный вход в систему")
    @Story("Позитивный сценарий входа")
    @Severity(SeverityLevel.CRITICAL)
    public void testSuccessfulLogin(String username, String password) {
        loginPage.performLogin(username, password);
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isUserLoggedIn(), "Вход в систему не удался с верными данными.");
    }

    @Test(dataProvider = "negativeLoginData", dataProviderClass = LoginProvider.class, description = "Ошибка при вводе неверных данных")
    @Story("Негативный сценарий входа")
    @Severity(SeverityLevel.CRITICAL)
    public void testFailedLogin(String username, String password) {
        loginPage.performLogin(username, password);
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username or password is incorrect"),
                "Сообщение об ошибке не отображается или текст некорректен.");
    }

    @Test(description = "Проверка состояния кнопки входа при пустых полях")
    @Story("Проверка интерфейса страницы входа")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginButtonState() {
        loginPage.fillLoginForm("", "");
        Assert.assertFalse(loginPage.isLoginButtonEnabled(), "Кнопка входа активна при пустых полях.");
    }
}