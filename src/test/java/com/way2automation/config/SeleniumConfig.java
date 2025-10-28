package com.way2automation.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class SeleniumConfig {
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
}
