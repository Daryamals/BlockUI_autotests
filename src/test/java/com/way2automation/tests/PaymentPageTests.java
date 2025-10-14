package com.way2automation.tests;

import com.way2automation.config.TestConfig;
import com.way2automation.pages.PaymentPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Заполнение многошаговой формы")
@Feature("Раздел Payment")
public class PaymentPageTests extends BaseTest {
    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        driver.get(TestConfig.getPaymentUrl());
    }

    @Test(description = "TC-03: Успешная отправка заполненной формы")
    @Story("Завершение и отправка формы")
    @Severity(SeverityLevel.CRITICAL)
    public void testPaymentSubmit() {
        String message = new PaymentPage(driver)
                .clickSubmit()
                .handleSuccessAlert()
                .getFinalMessage();
        Assert.assertTrue(message.contains("Test Completed, WooHoo!"),
                "Сообщение об успешной отправке некорректно.");
    }
}