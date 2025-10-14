package com.way2automation.tests;

import com.way2automation.config.TestConfig;
import com.way2automation.pages.InterestsPage;
import com.way2automation.pages.PaymentPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InterestsPageTests extends BaseTest {

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        driver.get(TestConfig.getInterestsUrl());
    }

    @Test(description = "TC-02: Успешный выбор интереса и переход с вкладки 'Interests'")
    public void testInterestsSection() {
        PaymentPage paymentPage = new InterestsPage(driver)
                .selectXboxInterest()
                .clickNextSection();
        Assert.assertTrue(paymentPage.isPaymentStepActive(), "Индикатор прогресса не переключился на шаг 'Payment'");
        Assert.assertTrue(paymentPage.getCurrentUrl().contains("/payment"), "URL не обновился на /payment");
    }
}