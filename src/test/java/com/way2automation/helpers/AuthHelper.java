package com.way2automation.helpers;

import com.way2automation.config.TestConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;

import java.net.URI;
import java.util.function.Predicate;

public class AuthHelper {
    private final WebDriver driver;

    public AuthHelper(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Регистрация учетных данных Basic Auth для пользователя '{username}'")
    public void registerBasicAuth(String username, String password) {
        Predicate<URI> uriPredicate = uri -> uri.getHost().contains(TestConfig.getHttpWatchAuthHost());
        if (driver instanceof HasAuthentication) {
            ((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of(username, password));
        } else {
            throw new UnsupportedOperationException("Данный веб-драйвер не поддерживает HasAuthentication.");
        }
    }
}