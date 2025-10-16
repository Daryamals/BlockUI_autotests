package com.way2automation.tests;

import com.way2automation.config.TestConfig;
import com.way2automation.data.TestData;
import com.way2automation.pages.HomePage;
import com.way2automation.pages.LoginPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Авторизация")
@Feature("Вход в систему")
public class LoginPageTests extends BaseTest {

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        driver.get(TestConfig.getLoginUrl());
        HomePage homePage = new HomePage(driver);
        if (homePage.isUserLoggedIn()) {
            homePage.logout();
        }
    }

    @DataProvider(name = "loginDataProvider")
    public Object[][] loginDataProvider() {
        return TestData.getLoginDataProvider();
    }

    @Test(dataProvider = "loginDataProvider", description = "Универсальный тест для проверки входа в систему")
    @Story("Сценарии входа в систему с различными данными")
    @Severity(SeverityLevel.CRITICAL)
    public void testLogin(String username, String password, String expectedResult) {
        LoginPage loginPage = new LoginPage(driver);
        switch (expectedResult) {
            case "success":
                loginPage.performLogin(username, password);
                HomePage homePage = new HomePage(driver);
                Assert.assertTrue(homePage.isUserLoggedIn(), "Вход в систему не удался с верными данными.");
                break;
            case "error":
                loginPage.performLogin(username, password);
                Assert.assertTrue(loginPage.getErrorMessage().contains("Username or password is incorrect"),
                        "Сообщение об ошибке не отображается или текст некорректен.");
                break;
            case "disabled":
                loginPage.fillLoginForm(username, password);
                Assert.assertFalse(loginPage.isLoginButtonEnabled(), "Кнопка входа активна при пустых полях.");
                break;
            default:
                Assert.fail("Неизвестный ожидаемый результат в тестовых данных: " + expectedResult);
        }
    }
}
