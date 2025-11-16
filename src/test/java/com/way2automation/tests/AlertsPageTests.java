package com.way2automation.tests;

import com.way2automation.data.AlertsProvider;
import com.way2automation.pages.AlertsPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Интерактивные элементы")
@Feature("JavaScript Alerts")
public class AlertsPageTests extends BaseTest {

    @Test(description = "U12: Успешная работа с Input Alert",
            dataProvider = "inputAlertData",
            dataProviderClass = AlertsProvider.class)
    public void testInputAlert(String name, String expectedText) {
        new AlertsPage(getDriver())
                .open()
                .triggerAndHandleInputAlert(name)
                .verifyResultTextIs(expectedText);
    }
}