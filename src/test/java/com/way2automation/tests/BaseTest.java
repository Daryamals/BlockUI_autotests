package com.way2automation.tests;

import com.way2automation.driver.Browser;
import com.way2automation.driver.DriverFactory;
import com.way2automation.helpers.JavaScriptHelper;
import com.way2automation.helpers.PageHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

public class BaseTest {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    protected PageHelper pageHelper;
    protected JavaScriptHelper jsHelper;

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @Parameters({"browser", "grid"})
    @BeforeMethod
    public void setUp(String browserName, String isGridStr) throws MalformedURLException {
        Browser browser = Browser.valueOf(browserName.toUpperCase());
        boolean isGrid = Boolean.parseBoolean(isGridStr);
        WebDriver driver = DriverFactory.createDriver(browser, isGrid);
        driver.manage().window().maximize();
        driverThreadLocal.set(driver);
        jsHelper = new JavaScriptHelper(getDriver());
        pageHelper = new PageHelper(jsHelper);
    }

    @AfterMethod
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
        }
        driverThreadLocal.remove();
    }
}