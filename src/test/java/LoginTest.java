import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import pageobject.MainPage;
import pageobject.PasswordPage;
import pageobject.RegistrationPage;

public class LoginTest extends Autostart {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка авторизации пользователя по логину и паролю - Войти в аккаунт")
    @Description("Проверяем работу кнопки - Войти в аккаунт - на странице меню и заказов")
    public void loginFromMainPageByEmailAndPassword() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickAccountButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        Assert.assertTrue("Создать заказ - не отображается", mainPage.isMainPageOpen());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка авторизации пользователя по логину и паролю - кнопка Личный кабинет")
    @Description("Проверяем работу кнопки - Личный кабинет - на странице меню и заказов")
    public void loginFromMainPageProfileButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickProfileButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        Assert.assertTrue("Создать заказ - не отображается", mainPage.isMainPageOpen());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка авторизации по кнопке - Войти - на странице - Регистрация")
    @Description("Вместо регистрации вводим e-mail и пароли и кнопку - Войти - внизу")
    public void loginFromRegistrationPage() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegisterPage()
                .clickEnterButtonOnRegistrationPage()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue("Создать заказ - не отображается", mainPage.isMainPageOpen());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка авторизации через страницу восстановления пароля")
    @Description("Вспомнили пароль? - жмем кнопку - Войти - и далее авторизуемся на странице Вход ")
    public void loginFromRecoveryPage() {
        PasswordPage passwordPage = new PasswordPage(driver);
        passwordPage.openRecoveryPage()
                .clickEnterButtonOnRecoveryPage()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue("Создать заказ - не отображается", mainPage.isMainPageOpen());
    }
}
