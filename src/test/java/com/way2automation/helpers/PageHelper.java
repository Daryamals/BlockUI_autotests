package com.way2automation.helpers;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageHelper {
    private static final Logger logger = LoggerFactory.getLogger(PageHelper.class);
    private final JavaScriptHelper jsHelper;

    public PageHelper(JavaScriptHelper jsHelper) {
        this.jsHelper = jsHelper;
    }

    @Step("Убираем фокус с активного элемента")
    public void blurActiveElement() {
        jsHelper.removeFocusFromActiveElement();
    }

    @Step("Проверяем наличие вертикального скролла")
    public boolean isVerticalScrollPresent() {
        return jsHelper.isVerticalScrollPresent();
    }

    @Step("Гарантируем, что на странице есть вертикальный скролл")
    public void ensureScrollbarIsPresent() {
        if (!isVerticalScrollPresent()) {
            logger.info("Скролл отсутствует. Искусственно увеличиваем высоту страницы.");
            jsHelper.setPageHeightForScroll("3000px");
        } else {
            logger.info("Скролл уже присутствует.");
        }
    }

    @Step("Гарантируем, что на странице отсутствует вертикальный скролл")
    public void ensureScrollbarIsAbsent() {
        if (isVerticalScrollPresent()) {
            logger.info("Скролл присутствует. Принудительно скрываем его через CSS.");
            jsHelper.hideScrollbar();
        } else {
            logger.info("Скролл уже отсутствует.");
        }
    }

    @Step("Сбрасываем все тестовые стили страницы")
    public void resetPageStyles() {
        jsHelper.resetPageStyles();
    }
}