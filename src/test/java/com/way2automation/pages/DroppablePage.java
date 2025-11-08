package com.way2automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

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

    @Step("Переключение на IFrame")
    private void switchToFrame() {
        driver.switchTo().frame(iframe);
    }

    @Step("Перетаскивание элемента")
    public DroppablePage performDragAndDrop() {
        switchToFrame();
        new Actions(driver)
                .dragAndDrop(draggableElement, droppableElement)
                .perform();
        driver.switchTo().defaultContent();
        return this;
    }

    @Step("Получение текста из принимающего элемента")
    public String getDroppableElementText() {
        switchToFrame();
        String text = waitHelper.waitForVisibilityOf(droppableElement).getText();
        driver.switchTo().defaultContent();
        return text;
    }
}