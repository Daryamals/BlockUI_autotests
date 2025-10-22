package com.way2automation.tests;

import com.way2automation.config.TestConfig;
import com.way2automation.data.SqlExLoginProvider;
import com.way2automation.helpers.CookieManager;
import com.way2automation.pages.SqlExPage;
import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Авторизация")
@Feature("Авторизация через Cookies на Sql-Ex.ru")
public class SqlExCookieTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(SqlExCookieTest.class);
    private final String COOKIE_FILE_PATH = "target/cookies.data";

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
    }

    @Test(priority = 1, dataProvider = "sqlExLoginData", dataProviderClass = SqlExLoginProvider.class, description = "Авторизация по логину/паролю и сохранение кук")
    @Story("Первичная авторизация")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginAndSaveCookies(String username, String password) {
        driver.get(TestConfig.getSqlExUrl());
        SqlExPage sqlExPage = new SqlExPage(driver);
        logger.info("Выполнение входа по логину/паролю для сохранения кук.");
        sqlExPage.login(username, password);
        Assert.assertTrue(sqlExPage.isUserLoggedIn(), "Ошибка входа по логину/паролю!");
        CookieManager.saveCookies(driver, COOKIE_FILE_PATH);
        logger.info("Куки успешно сохранены в {}", COOKIE_FILE_PATH);
    }

    @Test(priority = 2, description = "Авторизация с использованием сохраненных кук", dependsOnMethods = "testLoginAndSaveCookies")
    @Story("Вход с использованием сохраненных куков")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithCookies() {
        driver.get(TestConfig.getSqlExUrl());
        driver.manage().deleteAllCookies();
        CookieManager.loadCookies(driver, COOKIE_FILE_PATH);
        driver.navigate().refresh();
        SqlExPage sqlExPage = new SqlExPage(driver);
        Assert.assertTrue(sqlExPage.isUserLoggedIn(), "Не удалось войти с использованием Cookies!");
        logger.info("Успешный вход с использованием Cookies!");
    }
}