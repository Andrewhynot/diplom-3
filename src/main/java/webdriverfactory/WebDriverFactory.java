package webdriverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
    public static WebDriver driver;
    public static WebDriver getWebDriver(){
        if(driver == null){
            String browser = System.getProperty("browser", "yandex");
            switch (browser) {
                case "chrome":
                    return WebDriverManager.chromedriver().create();
                case "firefox":
                    return WebDriverManager.firefoxdriver().create();
                case "yandex":
                    System.setProperty("webdriver.chrome.driver", "C:\\cygwin64\\home\\And\\Diplom\\diplom-3\\src\\test\\resources\\yandexdriver.exe");
                    return new ChromeDriver();
                default:
                    throw new RuntimeException("Unsupported browser: " + browser);
            }
        }

        return driver;

    }



}
