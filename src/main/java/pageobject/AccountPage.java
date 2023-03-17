package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
    private static final By profileNameField = By.xpath(".//input[@name='Name']");
    private static final By burgerLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private static final By goToConstructor = By.xpath(".//p[text()='Конструктор']");
    private static final By logOutButton = By.xpath(".//button[text()='Выход']");
    private final WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Проверяем - отображается ли имя профиля?")
    public boolean isProfileNameFieldDisplayed() {
        return driver.findElement(profileNameField).isDisplayed();
    }

    @Step("Нажимаем кнопку - Конструктор")
    public void clickConstructorButton() {
        driver.findElement(goToConstructor).click();
    }

    @Step("Нажимаем на кнопку логотипа в шапке сайта")
    public void clickOnLogo() {
        driver.findElement(burgerLogo).click();
    }

    @Step("Нажимаем на кнопку - Выход - в личном кабинете")
    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
    }
}
