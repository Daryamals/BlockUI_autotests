package com.way2automation.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class TestConfig {

    private static final String CONFIG_FILE = "config.yaml";
    private static Map<String, Object> config;

    static {
        Yaml yaml = new Yaml();
        InputStream inputStream = TestConfig.class
                .getClassLoader()
                .getResourceAsStream(CONFIG_FILE);
        config = yaml.load(inputStream);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> getSeleniumMap() {
        return (Map<String, Object>) config.get("selenium");
    }

    public static String getSeleniumHubUrl() {
        return System.getProperty("selenium.hub", (String) getSeleniumMap().get("hub"));
    }

    public static String getDriversPath() {
        return (String) getSeleniumMap().get("drivers_path");
    }

    @SuppressWarnings("unchecked")
    private static Map<String, String> getDriverNamesMap() {
        return (Map<String, String>) getSeleniumMap().get("driver_names");
    }

    public static String getDriverPath(String browserName) {
        String driversBasePath = (String) getSeleniumMap().get("drivers_path");
        String driverName = getDriverNamesMap().get(browserName.toLowerCase());
        if (driverName == null)
            throw new IllegalArgumentException("Driver name for browser '" + browserName + "' not found in config.yaml");
        return driversBasePath + driverName;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, String> getUrlMap() {
        return (Map<String, String>) config.get("url");
    }

    public static String getBaseUrl() {
        return getUrlMap().get("base");
    }

    public static String getProfileUrl() {
        return getBaseUrl() + getUrlMap().get("profile");
    }

    public static String getInterestsUrl() {
        return getBaseUrl() + getUrlMap().get("interests");
    }

    public static String getPaymentUrl() {
        return getBaseUrl() + getUrlMap().get("payment");
    }

    public static String getProfileUrlPath() {
        return getUrlMap().get("profile");
    }

    public static String getInterestsUrlPath() {
        return getUrlMap().get("interests");
    }

    public static String getPaymentUrlPath() {
        return getUrlMap().get("payment");
    }

    public static String getLoginUrl() {
        return getUrlMap().get("login");
    }

    public static String getSqlExUrl() {
        return getUrlMap().get("sql_ex");
    }

    public static String getDroppableUrl() {
        return getUrlMap().get("droppable");
    }

    public static String getTabsUrl() {
        return getUrlMap().get("tabs");
    }

    public static String getAlertsUrl() {
        return getUrlMap().get("alert");
    }

    public static String getHttpWatchAuthUrl() {
        return getUrlMap().get("httpwatch_auth");
    }

    public static String getHttpWatchAuthHost() {
        return getUrlMap().get("httpwatch_auth_host");
    }


}