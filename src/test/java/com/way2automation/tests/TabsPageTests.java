package com.way2automation.tests;

import com.way2automation.config.TestConfig;
import com.way2automation.pages.TabsPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

@Epic("Интерактивные элементы")
@Feature("Вкладки и окна")
public class TabsPageTests extends BaseTest {

    private TabsPage tabsPage;

    @Parameters({"browser", "grid"})
    @BeforeMethod
    public void setUp(String browser, String isGrid) throws MalformedURLException {
        super.setUp(browser, isGrid);
        getDriver().get(TestConfig.getTabsUrl());
        tabsPage = new TabsPage(getDriver());
    }

    @Test(description = "U11: Работа с несколькими вкладками")
    @Story("Переключение между вкладками и проверка их количества")
    @Severity(SeverityLevel.CRITICAL)
    public void testMultipleTabsHandling() {
        Assert.assertEquals(getDriver().getWindowHandles().size(), 1, "Изначально должна быть открыта одна вкладка");
        tabsPage.clickToOpenSecondTab();
        Assert.assertEquals(getDriver().getWindowHandles().size(), 2, "Должно быть открыто две вкладки после первого клика");
        for (String handle : getDriver().getWindowHandles()) {
            if (!handle.equals(getDriver().getWindowHandle())) {
                getDriver().switchTo().window(handle);
                break;
            }
        }
        tabsPage.clickToOpenThirdTab();
        Assert.assertEquals(getDriver().getWindowHandles().size(), 3, "Должно быть открыто три вкладки после второго клика");
    }
}