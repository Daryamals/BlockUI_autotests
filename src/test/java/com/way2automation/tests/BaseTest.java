package com.way2automation.tests;

import com.way2automation.helpers.JavaScriptHelper;
import com.way2automation.helpers.PageHelper;
import com.way2automation.config.SeleniumConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected WebDriver driver;
    protected PageHelper pageHelper;
    protected JavaScriptHelper jsHelper;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL(SeleniumConfig.getSeleniumHubUrl()), new ChromeOptions());
        driver.manage().window().maximize();
        jsHelper = new JavaScriptHelper(driver);
        pageHelper = new PageHelper(jsHelper);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}