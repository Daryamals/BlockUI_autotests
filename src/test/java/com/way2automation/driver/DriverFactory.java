package com.way2automation.driver;

import com.way2automation.config.TestConfig;
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
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactory {

    private static final Map<Browser, Supplier<WebDriver>> LOCAL_DRIVER_MAP = Map.of(
            Browser.CHROME, () -> {
                ChromeOptions options = new ChromeOptions();
                options.setBrowserVersion("126");
                return new ChromeDriver(options);
            },
            Browser.FIREFOX, () -> {
                System.setProperty("webdriver.gecko.driver", TestConfig.getDriverPath("firefox"));
                return new FirefoxDriver(new FirefoxOptions());
            },
            Browser.EDGE, () -> {
                System.setProperty("webdriver.edge.driver", TestConfig.getDriverPath("edge"));
                return new EdgeDriver(new EdgeOptions());
            },
            Browser.IE, () -> {
                System.setProperty("webdriver.ie.driver", TestConfig.getDriverPath("ie"));
                return new InternetExplorerDriver(getIEOptions());
            }
    );

    private static final Map<Browser, Supplier<WebDriver>> REMOTE_DRIVER_MAP = Map.of(
            Browser.CHROME, () -> createRemoteDriver(new ChromeOptions()),
            Browser.FIREFOX, () -> createRemoteDriver(new FirefoxOptions()),
            Browser.EDGE, () -> createRemoteDriver(new EdgeOptions()),
            Browser.IE, () -> createRemoteDriver(getIEOptions())
    );

    public static WebDriver createDriver(Browser browser, boolean isGrid) {
        Supplier<WebDriver> driverSupplier = isGrid ?
                REMOTE_DRIVER_MAP.get(browser) :
                LOCAL_DRIVER_MAP.get(browser);

        if (driverSupplier == null) {
            throw new IllegalArgumentException("Unsupported browser type: " + browser);
        }
        return driverSupplier.get();
    }

    private static InternetExplorerOptions getIEOptions() {
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        ieOptions.setCapability("ignoreZoomSetting", true);
        ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        ieOptions.requireWindowFocus();
        ieOptions.setCapability("enablePersistentHover", false);
        return ieOptions;
    }

    private static WebDriver createRemoteDriver(org.openqa.selenium.Capabilities capabilities) {
        try {
            return new RemoteWebDriver(new URL(TestConfig.getSeleniumHubUrl()), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to create remote driver due to invalid Hub URL", e);
        }
    }
}