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
    private static Map<String, String> getSeleniumMap() {
        return (Map<String, String>) config.get("selenium");
    }

    public static String getSeleniumHubUrl() {
        return getSeleniumMap().get("hub");
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
}