package com.way2automation.tests;

import com.way2automation.data.BasicAuthProvider;
import com.way2automation.helpers.AuthHelper;
import com.way2automation.pages.BasicAuthPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Аутентификация")
@Feature("Basic Authentication")
public class BasicAuthTests extends BaseTest {
    @Test(description = "U13: Успешная Basic авторизация",
            dataProvider = "basicAuthCredentials",
            dataProviderClass = BasicAuthProvider.class)
    @Story("Прохождение Basic Auth")
    @Severity(SeverityLevel.CRITICAL)
    public void testBasicAuthentication(String username, String password) {
        new AuthHelper(getDriver()).registerBasicAuth(username, password);
        new BasicAuthPage(getDriver())
                .open()
                .clickDisplayImage()
                .verifyImageIsDisplayed();
    }
}