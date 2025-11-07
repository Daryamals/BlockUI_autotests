package com.way2automation.tests;

import com.way2automation.config.TestConfig;
import com.way2automation.pages.PaymentPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

@Epic("Заполнение многошаговой формы")
@Feature("Раздел Payment")
public class PaymentPageTests extends BaseTest {

    @Parameters({"browser", "grid"})
    @BeforeMethod
    public void setUp(String browser, String isGrid) throws MalformedURLException {
        super.setUp(browser, isGrid);
        getDriver().get(TestConfig.getPaymentUrl());
    }

    @Test(description = "TC-03: Успешная отправка заполненной формы")
    @Story("Завершение и отправка формы")
    @Severity(SeverityLevel.CRITICAL)
    public void testPaymentSubmit() {
        String message = new PaymentPage(getDriver())
                .clickSubmit()
                .handleSuccessAlert()
                .getFinalMessage();
        Assert.assertTrue(message.contains("Test Completed, WooHoo!"),
                "Сообщение об успешной отправке некорректно.");
    }
}