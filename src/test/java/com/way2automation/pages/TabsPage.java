package com.way2automation.pages;

import com.way2automation.config.TestConfig;
import com.way2automation.helpers.WindowHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class TabsPage extends BasePage {
    @FindBy(linkText = "New Browser Tab")
    private WebElement newTabLink;

    @FindBy(css = ".demo-frame")
    private WebElement iframe;

    private final WindowHelper windowHelper;

    public TabsPage(WebDriver driver) {
        super(driver);
        this.windowHelper = new WindowHelper(driver);
    }

    @Step("Открыть страницу Tabs")
    public TabsPage open() {
        driver.get(TestConfig.getTabsUrl());
        return this;
    }

    @Step("Кликнуть по ссылке для открытия новой вкладки")
    public TabsPage clickToOpenNewTab() {
        if (driver.getWindowHandles().size() == 1) {
            driver.switchTo().frame(iframe);
        }
        waitHelper.waitForElementToBeClickable(newTabLink).click();
        if (driver.getWindowHandles().size() == 1) {
            driver.switchTo().defaultContent();
        }
        windowHelper.switchToNewestTab();
        return this;
    }

    @Step("Проверить, что количество открытых вкладок равно {expectedCount}")
    public TabsPage verifyTabCountIs(int expectedCount) {
        int actualCount = driver.getWindowHandles().size();
        Assert.assertEquals(actualCount, expectedCount, "Количество открытых вкладок не соответствует ожидаемому.");
        return this;
    }
}