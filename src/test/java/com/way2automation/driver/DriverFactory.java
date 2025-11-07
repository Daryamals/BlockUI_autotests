package com.way2automation.driver;

import com.way2automation.config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    public static WebDriver createDriver(Browser browser, boolean isGrid) throws MalformedURLException {
        if (isGrid) {
            return createRemoteDriver(browser);
        } else {
            return createLocalDriver(browser);
        }
    }

    private static WebDriver createLocalDriver(Browser browser) {
        String driverPath = TestConfig.getDriversPath();

        switch (browser) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
                return new ChromeDriver(new ChromeOptions());
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
                return new FirefoxDriver(new FirefoxOptions());
            case EDGE:
                System.setProperty("webdriver.edge.driver", driverPath + "msedgedriver.exe");
                return new EdgeDriver(new EdgeOptions());
            case IE:
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability("ignoreZoomSetting", true);
                ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                return new InternetExplorerDriver(ieOptions);
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browser);
        }
    }

    private static WebDriver createRemoteDriver(Browser browser) throws MalformedURLException {
        URL hubUrl = new URL(TestConfig.getSeleniumHubUrl());
        switch (browser) {
            case CHROME:
                return new RemoteWebDriver(hubUrl, new ChromeOptions());
            case FIREFOX:
                return new RemoteWebDriver(hubUrl, new FirefoxOptions());
            case EDGE:
                return new RemoteWebDriver(hubUrl, new EdgeOptions());
            case IE:
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability("ignoreZoomSetting", true);
                ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                return new RemoteWebDriver(hubUrl, ieOptions);
            default:
                throw new IllegalArgumentException("Unsupported browser type for Grid execution: " + browser);
        }
    }
}