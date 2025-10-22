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

import java.io.File;

@Epic("Авторизация")
@Feature("Авторизация через Cookies на Sql-Ex.ru")
public class SqlExCookieTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(SqlExCookieTest.class);
    private static final String COOKIE_FILE_PATH = "target/cookies.data";
    private SqlExPage sqlExPage;

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        driver.get(TestConfig.getSqlExUrl());
        sqlExPage = new SqlExPage(driver);
        File cookieFile = new File(COOKIE_FILE_PATH);
        if (!cookieFile.exists()) {
            logger.info("Файл с куками не найден. Выполняется первичная авторизация для создания кук.");
            Object[][] data = SqlExLoginProvider.sqlExLoginData();
            String username = (String) data[0][0];
            String password = (String) data[0][1];
            sqlExPage.login(username, password);
            Assert.assertTrue(sqlExPage.isUserLoggedIn(), "Ошибка входа по логину/паролю при создании кук!");
            CookieManager.saveCookies(driver, COOKIE_FILE_PATH);
            logger.info("Куки для авторизации успешно созданы и сохранены.");
        }
    }

    @Test(description = "Авторизация с использованием сохраненных кук", dataProvider = "sqlExLoginData", dataProviderClass = SqlExLoginProvider.class)
    @Story("Вход с использованием сохраненных куков")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithCookies(String username, String password) {
        logger.info("Загрузка кук из файла: {}", COOKIE_FILE_PATH);
        driver.manage().deleteAllCookies();
        CookieManager.loadCookies(driver, COOKIE_FILE_PATH);
        driver.navigate().refresh();
        Assert.assertTrue(sqlExPage.isUserLoggedIn(), "Не удалось войти с использованием Cookies!");
        logger.info("Успешный вход с использованием Cookies!");
    }
}