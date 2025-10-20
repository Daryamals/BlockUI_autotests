package com.way2automation.pages;

import com.way2automation.config.TestConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {
    @FindBy(name = "name")
    private WebElement nameInput;
    @FindBy(name = "email")
    private WebElement emailInput;
    @FindBy(css = ".btn.btn-block.btn-info")
    private WebElement nextSectionButton;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод имени: {name}")
    public ProfilePage fillName(String name) {
        waitHelper.waitForElementToBeClickable(nameInput);
        nameInput.clear();
        nameInput.sendKeys(name);
        return this;
    }

    @Step("Ввод email: {email}")
    public ProfilePage fillEmail(String email) {
        waitHelper.waitForElementToBeClickable(emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Заполнение формы профиля с именем {name} и email {email}")
    public ProfilePage fillProfileForm(String name, String email) {
        fillName(name);
        fillEmail(email);
        return this;
    }

    @Step("Нажатие на кнопку 'Next Section'")
    public InterestsPage clickNextSection() {
        waitHelper.waitForElementToBeClickable(nextSectionButton).click();
        waitForUrlToContain(TestConfig.getInterestsUrlPath());
        return new InterestsPage(driver);
    }
}