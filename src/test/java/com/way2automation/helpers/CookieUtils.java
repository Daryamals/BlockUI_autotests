package com.way2automation.helpers;

import org.openqa.selenium.WebDriver;

public class CookieUtils {

    public static void saveCookiesToFile(WebDriver driver, String filePath) {
        CookieManager.saveCookies(driver, filePath);
    }

    public static void loadCookiesFromFile(WebDriver driver, String filePath) {
        driver.manage().deleteAllCookies();
        CookieManager.loadCookies(driver, filePath);
        driver.navigate().refresh();
    }
}