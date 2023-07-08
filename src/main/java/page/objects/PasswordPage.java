package page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class PasswordPage {

    private WebDriver driver;
    private By loginButton = By.linkText("Войти");

    public PasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход на страницу авторизации")
    public void loginButtonClick() {
        driver.findElement(loginButton).isEnabled();
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(loginButton));
        driver.findElement(loginButton).click();
    }

}