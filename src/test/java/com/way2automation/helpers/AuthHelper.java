package com.way2automation.helpers;

import com.way2automation.config.TestConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

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
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        if (augmentedDriver instanceof HasAuthentication) {
            ((HasAuthentication) augmentedDriver).register(uriPredicate, UsernameAndPassword.of(username, password));
        } else {
            throw new UnsupportedOperationException("Данный веб-драйвер не поддерживает HasAuthentication.");
        }
    }
}