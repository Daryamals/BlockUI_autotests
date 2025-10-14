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

    public static String getBaseUrl() {
        return (String) config.get("baseUrl");
    }
}