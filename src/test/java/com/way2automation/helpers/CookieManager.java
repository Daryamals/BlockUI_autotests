package com.way2automation.helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class CookieManager {
    private static final Logger logger = LoggerFactory.getLogger(CookieManager.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void saveCookies(WebDriver driver, String filePath) {
        try {
            Set<Cookie> cookies = driver.manage().getCookies();
            logger.info("Сохраняется {} кук: {}", cookies.size(), cookies);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), cookies);
            logger.info("Куки успешно сохранены в {}", filePath);
        } catch (IOException e) {
            logger.error("Ошибка при сохранении кук: {}", e.getMessage(), e);
        }
    }

    public static void loadCookies(WebDriver driver, String filePath) {
        try {
            File cookieFile = new File(filePath);
            if (cookieFile.exists()) {
                Set<Cookie> cookies = objectMapper.readValue(cookieFile, new TypeReference<Set<Cookie>>() {
                });
                logger.info("Загружено {} кук: {}", cookies.size(), cookies);
                for (Cookie cookie : cookies) {
                    driver.manage().addCookie(cookie);
                }
                logger.info("Куки успешно загружены из {}", filePath);
            } else {
                logger.warn("Файл кук {} не существует", filePath);
            }
        } catch (IOException e) {
            logger.error("Ошибка при загрузке кук: {}", e.getMessage(), e);
        }
    }
}