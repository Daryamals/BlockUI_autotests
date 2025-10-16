package com.way2automation.data;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class TestData {

    private static final String TEST_DATA_FILE = "data/testdata.yaml";
    private static final Map<String, Object> data;

    static {
        Yaml yaml = new Yaml();
        InputStream inputStream = TestData.class
                .getClassLoader()
                .getResourceAsStream(TEST_DATA_FILE);
        data = yaml.load(inputStream);
    }

    @SuppressWarnings("unchecked")
    public static List<Map<String, String>> getLoginCredentials() {
        return (List<Map<String, String>>) data.get("login_credentials");
    }

    public static Object[][] getLoginDataProvider() {
        List<Map<String, String>> credentials = getLoginCredentials();
        return credentials.stream()
                .map(cred -> new Object[]{cred.get("username"), cred.get("password"), cred.get("expectedResult")})
                .toArray(Object[][]::new);
    }
}