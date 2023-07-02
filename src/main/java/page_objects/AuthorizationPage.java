package page_objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthorizationPage {

    private WebDriver driver;
    private By emailField = By.xpath(".//fieldset[1]//input");
    private By passwordField = By.xpath("//input[@name = 'Пароль']");
    private By loginButton = By.xpath(".//button[text()='Войти']");

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Авторизация")
    public void authorization(String email, String password) {
        driver.findElement(loginButton).isDisplayed();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}