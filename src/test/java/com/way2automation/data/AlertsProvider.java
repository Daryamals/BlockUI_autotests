package com.way2automation.data;

import org.testng.annotations.DataProvider;

public class AlertsProvider {

    @DataProvider(name = "inputAlertData")
    public static Object[][] getInputAlertData() {
        return new Object[][]{
                {"Harry Potter", "Hello Harry Potter! How are you today?"},
                {"Albus Dumbledore", "Hello Albus Dumbledore! How are you today?"},
                {"", "Hello ! How are you today?"},
                {"12345", "Hello 12345! How are you today?"}
        };
    }
}