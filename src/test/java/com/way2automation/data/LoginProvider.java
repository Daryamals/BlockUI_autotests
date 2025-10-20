package com.way2automation.data;

import org.testng.annotations.DataProvider;

public class LoginProvider {

    @DataProvider(name = "positiveLoginData")
    public static Object[][] positiveLoginData() {
        return new Object[][]{
                {"angular", "password"}
        };
    }

    @DataProvider(name = "negativeLoginData")
    public static Object[][] negativeLoginData() {
        return new Object[][]{
                {"invalidUsername", "invalidPassword"}
        };
    }
}