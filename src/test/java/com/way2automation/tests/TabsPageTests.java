package com.way2automation.tests;

import com.way2automation.pages.TabsPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Интерактивные элементы")
@Feature("Вкладки и окна")
public class TabsPageTests extends BaseTest {

    @Test(description = "U11: Работа с несколькими вкладками")
    @Story("Переключение между вкладками и проверка их количества")
    @Severity(SeverityLevel.CRITICAL)
    public void testMultipleTabsHandling() {
        new TabsPage(getDriver())
                .open()
                .verifyTabCountIs(1)
                .clickToOpenNewTab()
                .verifyTabCountIs(2)
                .clickToOpenNewTab()
                .verifyTabCountIs(3);
    }
}