import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import pageobject.AuthorizationPage;
import pageobject.MainPage;


public class LogoutTest extends Autostart {


    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка выхода из профиля по кнопке - Выход - слева в личном кабинете пользователя")
    @Description("Проверяем что после выхода из профиля отображается страница авторизации - Вход")
    public void logoutSuccess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickAccountButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton()
                .clickProfileButtonFromAuthorizedUser()
                .clickLogOutButton();
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        Assert.assertTrue("Вход - не отображается", authorizationPage.isLoginIndicatorDispayed()); // добавил поверку отображения индикатра страницы
    }
}
