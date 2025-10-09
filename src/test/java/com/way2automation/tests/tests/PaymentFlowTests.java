package com.way2automation.tests.tests;

import com.way2automation.tests.base.BaseTest;
import com.way2automation.tests.pages.InterestsPage;
import com.way2automation.tests.pages.PaymentPage;
import com.way2automation.tests.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PaymentFlowTests extends BaseTest {
    @BeforeClass
    @Override
    public void setUp() {
        super.setUp();
        driver.get("https://www.way2automation.com/angularjs-protractor/multiform/#/form/profile");
    }

    @Test(priority = 1, description = "TC-01: Успешное заполнение и переход с вкладки 'Profile'")
    public void testProfileSection() {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.fillName("John Doe");
        profilePage.fillEmail("john.doe@test.com");
        InterestsPage interestsPage = profilePage.clickNextSection();
        Assert.assertTrue(interestsPage.isInterestsStepActive(), "Индикатор прогресса не переключился на шаг 'Interests'");
    }

    @Test(priority = 2, dependsOnMethods = "testProfileSection", description = "TC-02: Успешный выбор интереса и переход с вкладки 'Interests'")
    public void testInterestsSection() {
        InterestsPage interestsPage = new InterestsPage(driver);
        interestsPage.selectXboxInterest();
        PaymentPage paymentPage = interestsPage.clickNextSection();
        Assert.assertTrue(paymentPage.isPaymentStepActive(), "Индикатор прогресса не переключился на шаг 'Payment'");
        Assert.assertTrue(paymentPage.getCurrentUrl().contains("/payment"), "URL не обновился на /payment");
    }

    @Test(priority = 3, dependsOnMethods = "testInterestsSection", description = "TC-03: Успешная отправка заполненной формы")
    public void testPaymentSectionAndSubmit() {
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.clickSubmit();
        paymentPage.handleSuccessAlert();
        Assert.assertEquals(paymentPage.getFinalMessage(), "Test Completed, WooHoo!", "Сообщение об успешной отправке некорректно.");
    }
}