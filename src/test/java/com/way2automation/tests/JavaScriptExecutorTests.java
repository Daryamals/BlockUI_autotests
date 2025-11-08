package com.way2automation.tests;

import com.way2automation.config.TestConfig;
import com.way2automation.pages.ProfilePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

@Epic("UI Утилиты")
@Feature("JavaScriptExecutor")
public class JavaScriptExecutorTests extends BaseTest {

    private ProfilePage profilePage;

    @Parameters({"browser", "grid"})
    @BeforeMethod
    public void setUp(String browser, String isGrid) throws MalformedURLException {
        super.setUp(browser, isGrid);
        getDriver().get(TestConfig.getProfileUrl());
        profilePage = new ProfilePage(getDriver());
    }

    @AfterMethod
    public void cleanup() {
        if (pageHelper != null) {
            pageHelper.resetPageStyles();
        }
    }

    @Test(description = "Проверка снятия фокуса с элемента с помощью JavaScriptExecutor")
    @Story("JS: Взаимодействие с фокусом элемента")
    @Severity(SeverityLevel.NORMAL)
    public void testRemoveFocusWithJavaScript() {
        profilePage.fillName("Test User");
        Assert.assertEquals(getDriver().switchTo().activeElement(), profilePage.getNameInput(), "Предусловие не выполнено: поле 'Name' не в фокусе.");
        pageHelper.blurActiveElement();
        String activeElementTag = getDriver().switchTo().activeElement().getTagName();
        Assert.assertEquals(activeElementTag.toLowerCase(), "body", "Фокус не был убран с поля ввода.");
    }

    @Test(description = "Проверка, что JS-хелпер корректно определяет изменение состояния скролла")
    @Story("JS: Определение наличия скролла")
    @Severity(SeverityLevel.NORMAL)
    public void testScrollbarStateChanges() {
        pageHelper.ensureScrollbarIsAbsent();
        Assert.assertFalse(pageHelper.isVerticalScrollPresent(), "Предусловие нарушено: не удалось убрать скролл.");
        pageHelper.ensureScrollbarIsPresent();
        Assert.assertTrue(pageHelper.isVerticalScrollPresent(), "JS-хелпер не определил появление скролла.");
        pageHelper.ensureScrollbarIsAbsent();
        Assert.assertFalse(pageHelper.isVerticalScrollPresent(), "JS-хелпер не определил исчезновение скролла.");
    }
}