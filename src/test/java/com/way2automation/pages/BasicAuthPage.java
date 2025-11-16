package com.way2automation.pages;

import com.way2automation.config.TestConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class BasicAuthPage extends BasePage {

    @FindBy(id = "displayImage")
    private WebElement displayImageButton;
    @FindBy(id = "downloadImg")
    private WebElement protectedImage;

    public BasicAuthPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу Basic Auth")
    public BasicAuthPage open() {
        driver.get(TestConfig.getHttpWatchAuthUrl());
        return this;
    }

    @Step("Нажать на кнопку 'Display Image'")
    public BasicAuthPage clickDisplayImage() {
        waitHelper.waitForElementToBeClickable(displayImageButton).click();
        return this;
    }

    @Step("Проверить, что изображение после авторизации успешно отображается")
    public BasicAuthPage verifyImageIsDisplayed() {
        boolean isDisplayed = isImageDisplayed();
        Assert.assertTrue(isDisplayed, "Авторизация не пройдена, изображение не отображается.");
        return this;
    }

    private boolean isImageDisplayed() {
        try {
            waitHelper.waitForVisibilityOf(protectedImage);
            return protectedImage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}