package com.way2automation.tests;

import com.way2automation.config.TestConfig;
import com.way2automation.pages.AlertsPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

@Epic("Интерактивные элементы")
@Feature("JavaScript Alerts")
public class AlertsPageTests extends BaseTest {
    private AlertsPage alertsPage;

    @Parameters({"browser", "grid"})
    @BeforeMethod
    public void setUp(String browser, String isGrid) throws MalformedURLException {
        super.setUp(browser, isGrid);
        getDriver().get(TestConfig.getAlertsUrl());
        alertsPage = new AlertsPage(getDriver());
    }

    @Test(description = "U12: Успешная работа с Input Alert")
    public void testInputAlert() {
        final String name = "Harry Potter";
        final String expectedText = "Hello " + name + "! How are you today?";
        alertsPage.triggerInputAlert()
                .handleAlert(name);
        String actualText = alertsPage.getResultText(expectedText);
        Assert.assertEquals(actualText, expectedText, "Итоговый текст не соответствует ожидаемому.");
    }
}