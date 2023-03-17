import data.*;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pojo.User;
import pageobject.*;

import java.time.Duration;

import static data.UniformResourceLocator.*;

public class WrongPassTest {
    private WebDriver driver;
    private User user;

    @Before
    @Step("Подготовка к тестам - запускаем браузер")
    public void setUp() {
        driver = ChangeBrowser.getBrowser(CHROME);
        // driver = ChangeBrowser.getBrowser(CHROME_WDM); // chrome с зависимостью WebDriverManager
        // driver = ChangeBrowser.getBrowser(YANDEX); // проверен запуск Яндекс Браузера
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Пробуем зарегистрировать аккаунт указав пароль менее 6 символов")
    @Description("Вводим в поле пароль комбинацию 5 символов - сообщение - Некорректный пароль")
    public void shownErrorMessageWithShortPassword() {
        user = UserRandomizer.getNewRandomUserPassFive();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegisterPage()
                .enterRegisterName(user.getName())
                .enterRegisterEmail(user.getEmail())
                .enterRegisterPassword(user.getPassword())
                .clickRegistrationButton();
        Assert.assertEquals("Сообщение об ошибке в пароле не появилось",
                "Некорректный пароль",
                driver.findElement(RegistrationPage.registerWrongPasswordMessage).getText());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Пробуем зарегистрироваться с пробелом в поле пароль")
    @Description("Вводим в поле пароль один пробел - cообщение - Некорректный пароль")
    public void shownErrorMessageWithEmptyPassword() {
        user = UserRandomizer.getNewRandomUser();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegisterPage()
                .enterRegisterName(user.getName())
                .enterRegisterEmail(user.getEmail())
                .enterRegisterPassword(" ")
                .clickRegistrationButton();
        Assert.assertEquals("Сообщение о неверном пароле не появилось",
                WRONG_PASSWORD_MESSAGE,
                driver.findElement(RegistrationPage.registerWrongPasswordMessage).getText());
    }

    @After
    @Step("Закрываем браузер")
    public void tearDown() {
        driver.quit();
    }
}
