package com.way2automation.data;

import org.testng.annotations.DataProvider;

public class BasicAuthProvider {

    @DataProvider(name = "basicAuthCredentials")
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"httpwatch", "httpwatch"}
        };
    }
}