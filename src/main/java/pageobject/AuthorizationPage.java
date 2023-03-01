package pageobject;


import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorizationPage {
    private static final By loginEnterButton = By.xpath(".//*[text()='Войти']");
    private static final By loginEmail = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private static final By loginPassword = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");
    private static final By registerButtonFromLogin = By.xpath(".//*[text()='Зарегистрироваться']");
    private static final By registerWrongPasswordMessageInLoginPage = By.xpath(".//p[text()='Некорректный пароль']");
    private final WebDriver driver;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем страницу авторизации - Вход")
    public AuthorizationPage openLoginPage() {
        this.driver.get("https://stellarburgers.nomoreparties.site/login");
        return this;
    }

    @Step("Нажимаем кнопку - Зарегистрироваться")
    public RegistrationPage clickRegisterButtonLoginPage() {
        this.driver.findElement(registerButtonFromLogin).click();
        return new RegistrationPage(this.driver);
    }

    @Step("Нажимаем кнопку - Войти - на странице Вход (авторизация по e-mail и паролю)")
    public MainPage clickLoginEnterButton() {
        this.driver.findElement(loginEnterButton).click();
        return new MainPage(this.driver);
    }

    @Step("Заполняем поля формы авторизации - e-mail  и пароль Пользователя")
    public AuthorizationPage authorizationFromLoginPage(String email, String password) {
        (new WebDriverWait(this.driver, Duration.ofSeconds(5L))).until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
        this.driver.findElement(loginEmail).click();
        this.driver.findElement(loginEmail).sendKeys(new CharSequence[]{email});
        this.driver.findElement(loginPassword).click();
        this.driver.findElement(loginPassword).sendKeys(new CharSequence[]{password});
        return this;
    }
}
