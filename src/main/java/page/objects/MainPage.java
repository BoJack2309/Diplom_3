package page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class MainPage {

    private WebDriver driver;
    private By loginButton = By.xpath("//*[text()= 'Войти в аккаунт']");
    private By createOrderButton = By.xpath("//*[text() = 'Оформить заказ']");
    private By headerLoginButton = By.linkText("Личный Кабинет");
    private By entityBunsButton = By.xpath(".//div/span[text()='Булки']");
    private By entitySaucesButton = By.xpath(".//div/span[text()='Соусы']");

    private By entityFillingsButton = By.xpath(".//div/span[text()='Начинки']");
    private By entityActiveButton = By.className("tab_tab_type_current__2BEPc");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getEntityBunsButton() {
        return entityBunsButton;
    }

    public By getEntityFillingsButton() {
        return entityFillingsButton;
    }

    public By getEntitySaucesButton() {
        return entitySaucesButton;
    }

    @Step("Нажатие на кнопку 'Войти в аккаунт'")
    public void loginButtonClick() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        WebElement element = driver.findElement(loginButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие на кнопку 'Личный кабинет'")
    public void headerLoginButtonClick() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(headerLoginButton));
        WebElement element = driver.findElement(headerLoginButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(headerLoginButton).click();
    }

    @Step("Нажатие на кнопку 'Булки'")
    public void entityBunsButtonClick() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(entityBunsButton));
        WebElement element = driver.findElement(entityBunsButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(entityBunsButton).click();
    }

    @Step("Нажатие на кнопку 'Соусы'")
    public void entitySaucesButtonClick() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(entitySaucesButton));
        WebElement element = driver.findElement(entitySaucesButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(entitySaucesButton).click();
    }

    @Step("Нажатие на кнопку 'Начинки'")
    public void entityFillingsButtonClick() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(entityFillingsButton));
        WebElement element = driver.findElement(entityFillingsButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(entityFillingsButton).click();
    }

    @Step("Получение активной кнопки меню")
    public String getEntityActiveButton() {
        new WebDriverWait(driver, 5);
        return driver.findElement(entityActiveButton).getText();
    }
}