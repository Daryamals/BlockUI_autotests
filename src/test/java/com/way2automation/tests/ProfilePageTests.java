package com.way2automation.tests;

import com.way2automation.config.TestConfig;
import com.way2automation.pages.InterestsPage;
import com.way2automation.pages.ProfilePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

@Epic("Заполнение многошаговой формы")
@Feature("Раздел Profile")
public class ProfilePageTests extends BaseTest {

    private ProfilePage profilePage;

    @Parameters({"browser", "grid"})
    @BeforeMethod
    public void setUp(String browser, String isGrid) throws MalformedURLException {
        super.setUp(browser, isGrid);
        getDriver().get(TestConfig.getProfileUrl());
        profilePage = new ProfilePage(getDriver());
    }

    @Story("Переход к разделу Interests")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "TC-01: Успешное заполнение и переход с вкладки 'Profile'")
    public void testProfileSection() {
        InterestsPage interestsPage = profilePage
                .fillProfileForm("John Doe", "john.doe@test.com")
                .clickNextSection();
        Assert.assertTrue(interestsPage.isInterestsStepActive(), "Индикатор прогресса не переключился на шаг 'Interests'");
        Assert.assertTrue(interestsPage.getCurrentUrl().contains(TestConfig.getInterestsUrlPath()), "URL не обновился на /interests");
    }

    @Story("Демонстрация скриншота при падении")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "Падающий тест: Проверка неверного URL после перехода")
    public void testIncorrectUrlAfterProfileSubmission() {
        profilePage
                .fillProfileForm("Test User", "test@user.com")
                .clickNextSection();
        Assert.assertTrue(getDriver().getCurrentUrl().contains(TestConfig.getPaymentUrlPath()),
                "После заполнения профиля должен был произойти переход на страницу Payment, но этого не случилось.");
    }
}