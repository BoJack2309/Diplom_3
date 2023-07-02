package page_objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private WebDriver driver;
    private By emailField = By.xpath(".//fieldset[2]//input");
    private By passwordField = By.xpath(".//input[@type= 'password']");
    private By nameField = By.xpath(".//fieldset[1]//input");

    private By loginButton = By.linkText("Войти");
    private By registerButton = By.xpath(".//button[text()= 'Зарегистрироваться']");

    private By passwordError = By.className("input__error");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Регистрация")
    public void registration(String email, String password, String name) {
        driver.findElement(nameField).isEnabled();
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).isEnabled();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).isEnabled();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(registerButton).click();
    }

    @Step("Некорректный пароль")
    public boolean incorrectPasswordError() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(passwordError));
        return driver.findElement(passwordError).isDisplayed();
    }

    @Step("Нажатие на кнопку 'Авторизация'")
    public void loginButtonClick () {
        driver.findElement(loginButton).isEnabled();
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(loginButton));
        driver.findElement(loginButton).click();
    }

}