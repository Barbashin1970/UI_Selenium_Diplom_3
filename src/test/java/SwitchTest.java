import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import pageobject.AuthorizationPage;
import pageobject.MainPage;

public class SwitchTest extends Autostart {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Входим в личный кабинет пользователя с главной страницы с меню - до авторизации")
    @Description("Проверяем что без авторизации - попадаем на страницу авторизации - Вход")
    public void clickOnProfileByUnauthorizedUserGoToLoginPage() {
        MainPage mainPage = new MainPage(driver);
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        mainPage.openMainPage();
        mainPage.clickProfileButton();
        Assert.assertTrue("Вход - не отображается", authorizationPage.isLoginIndicatorDispayed()); // добавил поверку отображения индикатра страницы
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Переход на главную страницу с меню из личного кабинета по кнопке - Конструктор")
    @Description("Проверяем - главная страница с меню отображается")
    public void switchFromProfileByClickToConstructorButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickProfileButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton()
                .clickProfileButtonFromAuthorizedUser()
                .clickConstructorButton();
        Assert.assertTrue("Собери бургер - не отображается", mainPage.isBurgerIndicatorDisplayed()); // добавил поверку отображения индикатра страницы
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Переход на главную страницу из личного кабинета по кнопке с логотипом компании в шапке")
    @Description("Проверяем - главная страница с меню отображается")
    public void switchFromProfileByClickOnLogoBurger() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickProfileButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton()
                .clickProfileButtonFromAuthorizedUser()
                .clickOnLogo();
        Assert.assertTrue("Собери бургер - не отображается", mainPage.isBurgerIndicatorDisplayed()); // добавил поверку отображения индикатра страницы

    }
}

