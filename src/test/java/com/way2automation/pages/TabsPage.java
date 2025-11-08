package com.way2automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TabsPage extends BasePage {
    @FindBy(css = ".demo-frame")
    private WebElement iframe;
    @FindBy(xpath = "//a[contains(text(),'New Browser Tab')]")
    private WebElement linkToOpenSecondTab;
    @FindBy(xpath = "//a[contains(text(),'New Browser Tab')]")
    private WebElement linkOnSecondTab;

    public TabsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по ссылке для открытия второй вкладки")
    public void clickToOpenSecondTab() {
        driver.switchTo().frame(iframe);
        waitHelper.waitForElementToBeClickable(linkToOpenSecondTab).click();
        driver.switchTo().defaultContent();
    }

    @Step("Клик по ссылке для открытия третьей вкладки")
    public void clickToOpenThirdTab() {
        waitHelper.waitForElementToBeClickable(linkOnSecondTab).click();
    }
}