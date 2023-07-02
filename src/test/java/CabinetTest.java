import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.AuthorizationPage;
import page_objects.CabinetPage;
import page_objects.MainPage;
import work_directory.Constants;
import work_directory.Login;
import work_directory.UserAPI;

import static org.junit.Assert.assertEquals;
import static work_directory.Constants.*;

public class CabinetTest extends TestRandomizer{

    private WebDriver driver;
    private String email;
    private String password;
    private String name;
    private UserAPI userAPI;
    private Login login;
    private String accessToken;


    @Before
    public void setUp() {
        email = Email;
        password = correctPassword;
        name = Name;
        userAPI = new UserAPI();
        login = new Login(email, password, name);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        ValidatableResponse userLoginRequest = userAPI.registerUser(login);
        this.accessToken = userLoginRequest.extract().path("accessToken");
        driver.get(LOGIN_PAGE_URL);
        new AuthorizationPage(driver).authorization(email, password);
    }

    @Test
    @DisplayName("Выход по кнопке 'Выйти' из личного кабинета")
    public void exitButtonClickTest() {
        new MainPage(driver).headerLoginButtonClick();
        new CabinetPage(driver).exitButtonClick();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход по кнопке 'Конструктор' из личного Кабинета")
    public void constructorButtonClickInCabinetPageTest() {
        new CabinetPage(driver).constructorButtonClick();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));
        assertEquals(MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход по клику на логотип")
    public void logoClickInCabinetPageTest() {
        new CabinetPage(driver).headerLogoClick();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));
        assertEquals(MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход по кнопке 'Личный кабинет'")
    public void gotoCabinetPageTest() {
        new MainPage(driver).headerLoginButtonClick();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.urlToBe(CABINET_PAGE_URL));
        assertEquals(CABINET_PAGE_URL, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        userAPI.deleteUser(accessToken);
        driver.quit();
    }
}