package page_objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import work_directory.Constants;

public class CabinetPage {

    private WebDriver driver;
    private By constructorButton = By.xpath(".//header//p[text()='Конструктор']");
    private By logo = By.className("AppHeader_header__logo__2D0X2");
    private By exitButton = By.xpath(".//button[text()='Выход']");

    public CabinetPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажатие на кнопку Конструктор")
    public void constructorButtonClick() {
        new WebDriverWait(driver, 60)
                .until(ExpectedConditions.elementToBeClickable(constructorButton));
        driver.findElement(constructorButton).click();
    }

    @Step("Нажатие на лого")
    public void headerLogoClick() {
        new WebDriverWait(driver, 60)
                .until(ExpectedConditions.elementToBeClickable(logo));
        driver.findElement(logo).click();
    }

    @Step("Нажатие на 'Выход'")
    public void exitButtonClick() {
        new WebDriverWait(driver, 60)
                .until(ExpectedConditions.elementToBeClickable(exitButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(exitButton));
        driver.findElement(exitButton).click();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.urlToBe(Constants.LOGIN_PAGE_URL));
    }

}