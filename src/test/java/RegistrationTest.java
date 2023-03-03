import data.ChangeBrowser;
import data.UserRandomizer;
import data.UserSteps;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pageobject.*;
import pojo.*;

import java.time.Duration;

import static data.UniformResourceLocator.*;

public class RegistrationTest {
    private WebDriver driver;
    private User user;

    @Before
    @Step("Запускаем браузер и готовим рандомные данные для регистрации аккаунта")
    public void setUp() {
        // driver = ChangeBrowser.getBrowser(YANDEX); // проверен запуск Яндекс Браузера
        // driver = ChangeBrowser.getBrowser(CHROME_WDM); // chrome с зависимостью WebDriverManager
        driver = ChangeBrowser.getBrowser(CHROME);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        user = UserRandomizer.getNewRandomUser();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Регистрация пользователя через страницу - Регистрация (пароль 6 символов)")
    @Description("Регистрация - Авторизация - Войти. Проверяем что главная страница отображается после входа в профиль")
    public void successRegistrationOnRegPage() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegisterPage()
                .registerNewUser(user)
                .clickRegistrationButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue("Создать заказ - не отображается", mainPage.isMainPageOpen());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Регистрация пользователя через страницу авторизации - Вход")
    @Description("Жмем кнопку - Зарегистрироваться - внизу на странице авторизации - Вход")
    public void successRegistrationOnLoginPage() {
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        authorizationPage.openLoginPage()
                .clickRegisterButtonLoginPage()
                .registerNewUser(user)
                .clickRegistrationButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue("Создать заказ - не отображается", mainPage.isMainPageOpen());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Регистрация пользователя из главной страницы с меню и возврат на главную страницу")
    @Description("Жмем по кнопке - Войти в аккаунт - Зарегистрироваться - Войти - попадаем на Главную к оформлению заказа")
    public void successRegistrationOnMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickAccountButton()
                .clickRegisterButtonLoginPage()
                .registerNewUser(user)
                .clickRegistrationButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        Assert.assertTrue("Создать заказ - не отображается", mainPage.isMainPageOpen());
    }

    @After
    @Step("Удаляем профиль пользователя и закрываем браузер")
    public void tearDown() {
        UserSteps userSteps = new UserSteps();
        Authorization authorization = new Authorization(user.getEmail(), user.getPassword());
        ValidatableResponse responseLoginUser = userSteps.loginUser(authorization);
        String accessToken = responseLoginUser
                .extract()
                .path("accessToken")
                .toString()
                .substring(7);
        userSteps.deleteUser(accessToken);
        driver.quit();
    }
}
