import data.ChangeBrowser;
import data.UserRandomizer;
import data.UserSteps;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pojo.User;

import java.time.Duration;

import static data.UniformResourceLocator.*;

public class Autostart {
    User user;
    String accessToken;
    WebDriver driver;
    UserSteps userSteps;

    @Before
    @Step("Шаг запуска браузера и создания профиля пользователя с токеном доступа")
    public void setUp() {
        // driver = ChangeBrowser.getBrowser(YANDEX); // проверен запуск Яндекс Браузера
        // driver = ChangeBrowser.getBrowser(CHROME_WDM); // chrome с зависимостью WebDriverManager
        driver = ChangeBrowser.getBrowser(CHROME);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        user = UserRandomizer.getNewRandomUser();
        userSteps = new UserSteps();
        ValidatableResponse responseCreateUser = userSteps.createUser(user);
        accessToken = responseCreateUser.extract().path("accessToken").toString().substring(7);
    }

    @After
    @Step("Если токен не пустой - то мы удаляем профиль пользователя по его токену и закрываем браузер")
    public void tearDown() {
        if (accessToken != null) {
            userSteps.deleteUser(accessToken);
        }
        driver.quit();
    }
}
