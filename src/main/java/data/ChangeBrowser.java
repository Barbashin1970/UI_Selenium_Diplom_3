package data;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChangeBrowser {

    public static WebDriver getBrowser(String browserName) {
        switch (browserName) {
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/main/java/resources/yandexdriver");
                ChromeOptions options = new ChromeOptions();
                options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                return new ChromeDriver(options);
            case "chromeWebDriverManager":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "chromeNoManager":
                return new ChromeDriver();
            default:
                throw new RuntimeException("Только Хром и Яндекс!");
        }
    }
}
