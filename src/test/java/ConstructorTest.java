import data.ChangeBrowser;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;

import java.time.Duration;

import static data.UniformResourceLocator.*;

public class ConstructorTest {
    private WebDriver driver;
    private MainPage mainPage;


    @Before
    @Step("Запускаем браузер и создаем Главную страницу")
    public void setUp() {
        driver = ChangeBrowser.getBrowser(CHROME);
        // driver = ChangeBrowser.getBrowser(CHROME_WDM); // chrome с зависимостью WebDriverManager
        // driver = ChangeBrowser.getBrowser(YANDEX); // проверен запуск Яндекс Браузера
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        mainPage = new MainPage(driver);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверяем меню - раздел Соусы")
    @Description("Проверяем что закладка - Соусы - выбрана и выделена белым шрифтом")
    public void menuSauceIsActiveByClick() {
        mainPage.openMainPage()
                .clickMenuFillings()
                .clickMenuSauce();
        Assert.assertEquals("Соусы", mainPage.getTextFromSelectedMenu());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверяем раздел меню - Булки")
    @Description("Проверяем что закладка - Булки - выбрана и выделена белым шрифтом")
    public void menuBunIsActiveByClick() {
        mainPage.openMainPage()
                .clickMenuSauce()
                .clickMenuBun();
        Assert.assertEquals("Булки", mainPage.getTextFromSelectedMenu());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверяем раздел меню - Начинки")
    @Description("Проверяем что закладка - Начинки - выбрана и выделена белым шрифтом")
    public void menuFillingsIsActiveByClick() {
        mainPage.openMainPage()
                .clickMenuSauce()
                .clickMenuFillings();
        Assert.assertEquals("Начинки", mainPage.getTextFromSelectedMenu());
    }

    @After
    @Step("Закрываем браузер")
    public void tearDown() {
        driver.quit();
    }
}
