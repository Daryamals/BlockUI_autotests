package com.way2automation.tests;

import com.way2automation.config.TestConfig;
import com.way2automation.pages.InterestsPage;
import com.way2automation.pages.PaymentPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

@Epic("Заполнение многошаговой формы")
@Feature("Раздел Interests")
public class InterestsPageTests extends BaseTest {

    @Parameters({"browser", "grid"})
    @BeforeMethod
    public void setUp(String browser, String isGrid) throws MalformedURLException {
        super.setUp(browser, isGrid);
        getDriver().get(TestConfig.getInterestsUrl());
    }

    @Test(description = "TC-02: Успешный выбор интереса и переход с вкладки 'Interests'")
    @Story("Переход к разделу Payment")
    @Severity(SeverityLevel.CRITICAL)
    public void testInterestsSection() {
        PaymentPage paymentPage = new InterestsPage(getDriver())
                .selectXboxInterest()
                .clickNextSection();
        Assert.assertTrue(paymentPage.isPaymentStepActive(), "Индикатор прогресса не переключился на шаг 'Payment'");
        Assert.assertTrue(paymentPage.getCurrentUrl().contains(TestConfig.getPaymentUrlPath()), "URL не обновился на /payment");
    }

    @Story("Демонстрация скриншота при падении")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "Падающий тест: Проверка, что шаг 'Profile' остался активным")
    public void testProfileStepIsStillActive() {
        InterestsPage interestsPage = new InterestsPage(getDriver());
        interestsPage.selectXboxInterest();
        Assert.assertFalse(interestsPage.isInterestsStepActive(), "Шаг 'Interests' не должен был стать активным.");
    }
}