package com.way2automation.helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptHelper {
    private final JavascriptExecutor js;

    public JavaScriptHelper(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    @Step("Убираем фокус с активного элемента с помощью JS")
    public void removeFocusFromActiveElement() {
        js.executeScript("document.activeElement.blur();");
    }

    @Step("Проверяем наличие вертикального скролла с помощью JS")
    public boolean isVerticalScrollPresent() {
        String script = "const isContentOverflowing = document.documentElement.scrollHeight > document.documentElement.clientHeight;" +
                "const overflowStyle = window.getComputedStyle(document.documentElement).overflowY;" +
                "const isScrollbarHidden = overflowStyle === 'hidden';" +
                "return isContentOverflowing && !isScrollbarHidden;";
        return (boolean) js.executeScript(script);
    }

    @Step("Принудительно изменяем высоту страницы для ПОЯВЛЕНИЯ скролла")
    public void setPageHeightForScroll(String height) {
        js.executeScript("document.body.style.height = arguments[0]", height);
    }

    @Step("Принудительно скрываем скролл через CSS overflow")
    public void hideScrollbar() {
        js.executeScript("document.documentElement.style.overflow = 'hidden';");
    }

    @Step("Сбрасываем все тестовые стили страницы (высоту и overflow)")
    public void resetPageStyles() {
        js.executeScript("document.body.style.height = ''; document.documentElement.style.overflow = 'auto';");
    }
}