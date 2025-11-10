package com.way2automation.pages;

import com.way2automation.config.TestConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class DroppablePage extends BasePage {

    @FindBy(css = ".demo-frame")
    private WebElement iframe;

    @FindBy(id = "draggable")
    private WebElement draggableElement;

    @FindBy(id = "droppable")
    private WebElement droppableElement;

    public DroppablePage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу Droppable")
    public DroppablePage open() {
        driver.get(TestConfig.getDroppableUrl());
        return this;
    }

    @Step("Выполнить перетаскивание элемента на цель")
    public DroppablePage dragAndDropElement() {
        driver.switchTo().frame(iframe);
        new Actions(driver)
                .dragAndDrop(draggableElement, droppableElement)
                .perform();
        driver.switchTo().defaultContent();
        return this;
    }

    @Step("Проверить, что текст цели изменился на '{expectedText}'")
    public DroppablePage verifyDroppableTextIs(String expectedText) {
        driver.switchTo().frame(iframe);
        String actualText = waitHelper.waitForVisibilityOf(droppableElement).getText();
        Assert.assertEquals(actualText, expectedText, "Текст принимающего элемента не соответствует ожидаемому.");
        driver.switchTo().defaultContent();
        return this;
    }
}