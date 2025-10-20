package com.way2automation.tests;

import com.way2automation.config.TestConfig;
import com.way2automation.pages.InterestsPage;
import com.way2automation.pages.ProfilePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Заполнение многошаговой формы")
@Feature("Раздел Profile")
public class ProfilePageTests extends BaseTest {

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        driver.get(TestConfig.getProfileUrl());
    }

    @Story("Переход к разделу Interests")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "TC-01: Успешное заполнение и переход с вкладки 'Profile'")
    public void testProfileSection() {
        InterestsPage interestsPage = new ProfilePage(driver)
                .fillProfileForm("John Doe", "john.doe@test.com")
                .clickNextSection();
        Assert.assertTrue(interestsPage.isInterestsStepActive(), "Индикатор прогресса не переключился на шаг 'Interests'");
        Assert.assertTrue(interestsPage.getCurrentUrl().contains(TestConfig.getInterestsUrlPath()), "URL не обновился на /interests");
    }

    @Story("Демонстрация скриншота при падении")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "Падающий тест: Проверка неверного URL после перехода")
    public void testIncorrectUrlAfterProfileSubmission() {
        new ProfilePage(driver)
                .fillProfileForm("Test User", "test@user.com")
                .clickNextSection();
        Assert.assertTrue(driver.getCurrentUrl().contains(TestConfig.getPaymentUrlPath()),
                "После заполнения профиля должен был произойти переход на страницу Payment, но этого не случилось.");
    }
}