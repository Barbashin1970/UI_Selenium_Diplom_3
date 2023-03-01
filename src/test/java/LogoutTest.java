import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.MainPage;

import java.time.Duration;

import static data.UniformResourceLocator.LOGIN_PAGE_URL;

public class LogoutTest extends Autostart {


    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка выхода из профиля по кнопке - Выход - слева в личном кабинете пользователя")
    @Description("Проверяем что после выхода из профиля отображается страница авторизации - Вход")
    public void logoutSuccess() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.openMainPage()
                .clickAccountButton()
                .authorizationFromLoginPage(this.user.getEmail(), this.user.getPassword())
                .clickLoginEnterButton()
                .clickProfileButtonFromAuthorizedUser()
                .clickLogOutButton();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        Assert.assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }
}
