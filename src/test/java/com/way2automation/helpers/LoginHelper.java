package com.way2automation.helpers;

import com.way2automation.data.SqlExLoginProvider;
import com.way2automation.pages.SqlExPage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class LoginHelper {

    private static final Logger logger = LoggerFactory.getLogger(LoginHelper.class);

    public static void performInitialLoginAndSaveCookies(WebDriver driver, String filePath) {
        Object[][] data = SqlExLoginProvider.sqlExLoginData();
        String username = (String) data[0][0];
        String password = (String) data[0][1];
        SqlExPage sqlExPage = new SqlExPage(driver);
        sqlExPage.login(username, password);
        Assert.assertTrue(sqlExPage.isUserLoggedIn(), "Ошибка входа по логину/паролю при создании кук!");
        CookieUtils.saveCookiesToFile(driver, filePath);
        logger.info("Куки для авторизации успешно созданы и сохранены в {}", filePath);
    }
}