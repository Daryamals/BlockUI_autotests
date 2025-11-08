package com.way2automation.tests;

import com.way2automation.config.TestConfig;
import com.way2automation.pages.DroppablePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

@Epic("Интерактивные элементы")
@Feature("Drag and Drop (IFrame)")
public class DroppablePageTests extends BaseTest {

    private DroppablePage droppablePage;

    @Parameters({"browser", "grid"})
    @BeforeMethod
    public void setUp(String browser, String isGrid) throws MalformedURLException {
        super.setUp(browser, isGrid);
        getDriver().get(TestConfig.getDroppableUrl());
        droppablePage = new DroppablePage(getDriver());
    }

    @Test(description = "U10: Успешное перетаскивание элемента в IFrame")
    @Story("Перетаскивание элемента")
    @Severity(SeverityLevel.CRITICAL)
    public void testDragAndDropInIframe() {
        droppablePage.performDragAndDrop();
        String newText = droppablePage.getDroppableElementText();
        Assert.assertEquals(newText, "Dropped!", "Текст принимающего элемента не изменился на 'Dropped!'");
    }
}