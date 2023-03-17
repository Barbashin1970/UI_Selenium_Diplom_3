package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pojo.User;

import static data.UniformResourceLocator.REGISTER_PAGE_URL;

public class RegistrationPage {
    private static final By registerName = By.xpath(".//label[text() = 'Имя']/../input[contains(@name, 'name')]");
    private static final By registerEmail = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private static final By registerPassword = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");
    private static final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private static final By enterButtonOnRegistrationPage = By.xpath(".//*[text()='Войти']");
    public static By registerWrongPasswordMessage = By.xpath(".//p[text()='Некорректный пароль']");
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем страницу - Регистрация")
    public RegistrationPage openRegisterPage() {
        driver.get(REGISTER_PAGE_URL);
        return this;
    }

    @Step("Вводим имя пользователя на странице Регистрация")
    public RegistrationPage enterRegisterName(String name) {
        driver.findElement(registerName).click();
        driver.findElement(registerName).sendKeys(name);
        return this;
    }

    @Step("Вводим e-mail на странице - Регистрация")
    public RegistrationPage enterRegisterEmail(String email) {
        driver.findElement(registerEmail).click();
        driver.findElement(registerEmail).sendKeys(email);
        return this;
    }

    @Step("Вводим пароль на странице - Регистрация")
    public RegistrationPage enterRegisterPassword(String password) {
        driver.findElement(registerPassword).click();
        driver.findElement(registerPassword).sendKeys(password);
        return this;
    }

    @Step("Нажимаем кнопку - Зарегистрироваться")
    public AuthorizationPage clickRegistrationButton() {
        driver.findElement(registrationButton).click();
        return new AuthorizationPage(driver);
    }

    @Step("Нажимаем кнопку - Войти - на странице - Регистрация")
    public AuthorizationPage clickEnterButtonOnRegistrationPage() {
        driver.findElement(enterButtonOnRegistrationPage).click();
        return new AuthorizationPage(driver);
    }

    @Step("Вводим имя, e-mail, пароль Пользователя - заполняем форму Регистрация")
    public RegistrationPage registerNewUser(User user) {
        driver.findElement(registerName).click();
        driver.findElement(registerName).sendKeys(user.getName());
        driver.findElement(registerEmail).click();
        driver.findElement(registerEmail).sendKeys(user.getEmail());
        driver.findElement(registerPassword).click();
        driver.findElement(registerPassword).sendKeys(user.getPassword());
        return this;
    }
}
