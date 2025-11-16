package com.way2automation.helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class WindowHelper {
    private final WebDriver driver;

    public WindowHelper(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переключиться на последнюю открытую вкладку")
    public void switchToNewestTab() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }
}