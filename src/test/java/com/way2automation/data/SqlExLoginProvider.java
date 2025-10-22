package com.way2automation.data;

import org.testng.annotations.DataProvider;

public class SqlExLoginProvider {

    @DataProvider(name = "sqlExLoginData")
    public static Object[][] sqlExLoginData() {
        return new Object[][]{
                {"Daryamals", "dashka2001"}
        };
    }
}