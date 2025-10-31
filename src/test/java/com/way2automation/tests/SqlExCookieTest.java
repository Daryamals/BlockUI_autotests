package com.way2automation.tests;

import com.way2automation.config.TestConfig;
import com.way2automation.helpers.CookieUtils;
import com.way2automation.helpers.FileUtils;
import com.way2automation.helpers.LoginHelper;
import com.way2automation.pages.SqlExPage;
import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

@Epic("Авторизация")
@Feature("Авторизация через Cookies на Sql-Ex.ru")
public class SqlExCookieTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(SqlExCookieTest.class);
    private static final String COOKIE_FILE_PATH = "target/cookies.data";
    private SqlExPage sqlExPage;

    @BeforeMethod
    @Override
    public void setUp() throws MalformedURLException {
        super.setUp();
        driver.get(TestConfig.getSqlExUrl());
        sqlExPage = new SqlExPage(driver);

        if (!FileUtils.fileExists(COOKIE_FILE_PATH)) {
            logger.info("Файл с куками не найден. Выполняется первичная авторизация для создания кук.");
            LoginHelper.performInitialLoginAndSaveCookies(driver, COOKIE_FILE_PATH);
        }
    }

    @Test(description = "Авторизация с использованием сохраненных кук")
    @Story("Вход с использованием сохраненных куков")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithCookies() {
        logger.info("Загрузка кук из файла: {}", COOKIE_FILE_PATH);
        CookieUtils.loadCookiesFromFile(driver, COOKIE_FILE_PATH);
        Assert.assertTrue(sqlExPage.isUserLoggedIn(), "Не удалось войти с использованием Cookies!");
        logger.info("Успешный вход с использованием Cookies!");
    }
}