package com.way2automation.tests;

import com.way2automation.config.TestConfig;
import com.way2automation.pages.InterestsPage;
import com.way2automation.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfilePageTests extends BaseTest {

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        driver.get(TestConfig.getBaseUrl());
    }

    @Test(description = "TC-01: Успешное заполнение и переход с вкладки 'Profile'")
    public void testProfileSection() {
        ProfilePage profilePage = new ProfilePage(driver);
        InterestsPage interestsPage = profilePage
                .fillName("John Doe")
                .fillEmail("john.doe@test.com")
                .clickNextSection();
        Assert.assertTrue(interestsPage.isInterestsStepActive(), "Индикатор прогресса не переключился на шаг 'Interests'");
        Assert.assertTrue(interestsPage.getCurrentUrl().contains("/interests"), "URL не обновился на /interests");
    }
}