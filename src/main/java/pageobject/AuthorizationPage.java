package pageobject;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static data.UniformResourceLocator.LOGIN_PAGE_URL;

public class AuthorizationPage {

    private static final By loginIndicator = By.xpath(".//*[text()='Вход']");
    private static final By loginEnterButton = By.xpath(".//*[text()='Войти']");
    private static final By loginEmail = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private static final By loginPassword = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");
    private static final By registerButtonFromLogin = By.xpath(".//*[text()='Зарегистрироваться']");

    private final WebDriver driver;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем страницу авторизации - Вход")
    public boolean isLoginIndicatorDispayed() {
        return driver.findElement(loginIndicator).isDisplayed();
    }

    @Step("Открываем страницу авторизации - Вход")
    public AuthorizationPage openLoginPage() {
        driver.get(LOGIN_PAGE_URL);
        return this;
    }

    @Step("Нажимаем кнопку - Зарегистрироваться")
    public RegistrationPage clickRegisterButtonLoginPage() {
        driver.findElement(registerButtonFromLogin).click();
        return new RegistrationPage(driver);
    }

    @Step("Нажимаем кнопку - Войти - на странице Вход (авторизация по e-mail и паролю)")
    public MainPage clickLoginEnterButton() {
        driver.findElement(loginEnterButton).click();
        return new MainPage(driver);
    }

    @Step("Заполняем поля формы авторизации - e-mail  и пароль Пользователя")
    public AuthorizationPage authorizationFromLoginPage(String email, String password) {
        (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        driver.findElement(loginEmail).click();
        driver.findElement(loginEmail).sendKeys(email);
        driver.findElement(loginPassword).click();
        driver.findElement(loginPassword).sendKeys(password);
        return this;
    }
}
