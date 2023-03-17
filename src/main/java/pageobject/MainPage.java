package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static data.UniformResourceLocator.BASE_URL;

public class MainPage {
    private static final By profileMainButton = By.xpath(".//p[text()='Личный Кабинет']");
    private static final By menuBun = By.xpath(".//span[text()='Булки']");
    private static final By menuFillings = By.xpath(".//span[text()='Начинки']");
    private static final By currentMenu = By.xpath("//div[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]");
    private static final By makeOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private static final By setBurgerIndicator = By.xpath(".//*[text()='Соберите бургер']");
    private static final By menuSauce = By.xpath(".//span[text()='Соусы']");
    private static final By enterAccountButtonMain = By.xpath("//button[text()='Войти в аккаунт']");
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем главную страницу")
    public MainPage openMainPage() {
        driver.get(BASE_URL);
        return this;
    }

    @Step("Наживаем кнопку - Войти в аккаунт - справа на странице с меню")
    public AuthorizationPage clickAccountButton() {
        driver.findElement(enterAccountButtonMain).click();
        return new AuthorizationPage(driver);
    }

    @Step("Нажимаем на кнопку - Личный кабинет - пользователь не авторизован")
    public AuthorizationPage clickProfileButton() {
        driver.findElement(profileMainButton).click();
        return new AuthorizationPage(driver);
    }

    @Step("Нажимаем на кнопку - Личный кабинет - пользователь авторизован")
    public AccountPage clickProfileButtonFromAuthorizedUser() {
        driver.findElement(profileMainButton).click();
        return new AccountPage(driver);
    }

    @Step("Проверяем отображается ли кнопка - Оформить заказ - главная страница")
    public boolean isMainPageOpen() {
        return driver.findElement(makeOrderButton).isDisplayed();
    }
    @Step("Проверяем отображается ли кнопка - Оформить заказ - главная страница")
    public boolean isBurgerIndicatorDisplayed() {
        return driver.findElement(setBurgerIndicator).isDisplayed();
    }

    @Step("Нажать на закладку - Булки")
    public MainPage clickMenuBun() {
        driver.findElement(menuBun).click();
        return this;
    }

    @Step("Нажать на закладку - Соусы")
    public MainPage clickMenuSauce() {
        driver.findElement(menuSauce).click();
        return this;
    }

    @Step("Нажать на закладку - Начинки")
    public MainPage clickMenuFillings() {
        driver.findElement(menuFillings).click();
        return this;
    }

    @Step("Проверяем текст текущего меню")
    public String getTextFromSelectedMenu() {
        return driver.findElement(currentMenu).getText();
    }
}
