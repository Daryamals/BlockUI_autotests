package com.way2automation.tests;

import com.way2automation.pages.DroppablePage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Интерактивные элементы")
@Feature("Drag and Drop (IFrame)")
public class DroppablePageTests extends BaseTest {

    @Test(description = "U10: Успешное перетаскивание элемента в IFrame")
    @Story("Перетаскивание элемента")
    @Severity(SeverityLevel.CRITICAL)
    public void testDragAndDropInIframe() {
        new DroppablePage(getDriver())
                .open()
                .dragAndDropElement()
                .verifyDroppableTextIs("Dropped!");
    }
}