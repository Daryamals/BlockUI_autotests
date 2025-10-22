package com.way2automation.helpers;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;

public class CookieManager {

    private static final Logger logger = LoggerFactory.getLogger(CookieManager.class);

    public static void saveCookies(WebDriver driver, String filePath) {
        File file = new File(filePath);
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (Cookie cookie : driver.manage().getCookies()) {
                writer.write(cookie.getName() + ";" +
                        cookie.getValue() + ";" +
                        cookie.getDomain() + ";" +
                        cookie.getPath() + ";" +
                        (cookie.getExpiry() != null ? cookie.getExpiry().getTime() : "null") + ";" +
                        cookie.isSecure() + ";" +
                        cookie.isHttpOnly());
                writer.newLine();
            }
            logger.info("Куки успешно сохранены в {}", filePath);
        } catch (IOException e) {
            logger.error("Ошибка при сохранении куков: {}", e.getMessage());
        }
    }

    public static void loadCookies(WebDriver driver, String filePath) {
        File file = new File(filePath);
        try (FileReader fileReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(";", 7);
                if (tokens.length >= 7) {
                    String name = tokens[0];
                    String value = tokens[1];
                    String domain = tokens[2];
                    String path = tokens[3];
                    Date expiry = null;
                    if (!"null".equals(tokens[4])) {
                        try {
                            expiry = new Date(Long.parseLong(tokens[4]));
                        } catch (NumberFormatException e) {
                            logger.warn("Не удалось распарсить дату для куки '{}'. Кука будет сессионной.", name);
                        }
                    }
                    boolean isSecure = Boolean.parseBoolean(tokens[5]);
                    boolean isHttpOnly = Boolean.parseBoolean(tokens[6]);
                    Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure, isHttpOnly);
                    driver.manage().addCookie(cookie);
                }
            }
            logger.info("Куки успешно загружены из {}", filePath);
        } catch (IOException e) {
            logger.error("Ошибка при загрузке куков: {}", e.getMessage());
        }
    }
}