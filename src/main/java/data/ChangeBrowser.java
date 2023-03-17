package data;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChangeBrowser {

    public static WebDriver getBrowser(String browserName) {
        switch (browserName) {

            case "yandex":
                System.setProperty("webdriver.chrome.driver", "/Users/olegbarbashin/DIPLOM/Diplom_3/src/main/resources/chromedriver_108");
                // System.setProperty("webdriver.chrome.driver", "/Users/olegbarbashin/DIPLOM/Diplom_3/src/main/resources/chromedriver");
                // для версии Яндекс-Браузер версии v.21.5.0.751 (Mac OS X Puma) chromedriver - версия 90.0.4430.24
                ChromeOptions options = new ChromeOptions();
                options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                return new ChromeDriver(options);

            case "chromeWebDriverManager":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            case "chromeNoManager":
                return new ChromeDriver();

            default:
                throw new RuntimeException("Ваш браузер пока не поддерживается - только Хром и Яндекс!");
        }
    }
}
