package work.directory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WebDriverSetUp {

    private static WebDriver driver = null;
    private static FileInputStream fileInputStream;

    public static WebDriver runDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        try {
            fileInputStream = new FileInputStream("src/main/java/resources/browser.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);

            switch (properties.getProperty("browser.name")) {
                case "chrome":
                case "yandex":
                    options.setBinary("C:\\Users\\812427\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
                    driver = new ChromeDriver(options);
                    break;
                default:
                    driver = new ChromeDriver(options);
            }
        }  catch (IOException exception) {
            exception.printStackTrace();
            driver = new ChromeDriver(options);
        }
        return driver;
    }
    public static void stopDriver() {
        driver.quit();
        driver = null;
    }
}