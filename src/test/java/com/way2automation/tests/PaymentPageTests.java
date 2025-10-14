package com.way2automation.tests;

import com.way2automation.config.TestConfig;
import com.way2automation.pages.InterestsPage;
import com.way2automation.pages.PaymentPage;
import com.way2automation.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PaymentPageTests extends BaseTest {
    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        driver.get(TestConfig.getBaseUrl());
        new ProfilePage(driver)
                .fillProfileForm("Test User", "test@test.com")
                .clickNextSection();
        new InterestsPage(driver)
                .selectXboxInterest()
                .clickNextSection();
    }

    @Test(description = "TC-03: Успешная отправка заполненной формы")
    public void testPaymentSubmit() {
        String message = new PaymentPage(driver)
                .clickSubmit()
                .handleSuccessAlert()
                .getFinalMessage();
        Assert.assertTrue(message.contains("Test Completed, WooHoo!"),
                "Сообщение об успешной отправке некорректно.");
    }
}