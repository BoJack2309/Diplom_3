import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.objects.AuthorizationPage;
import page.objects.CabinetPage;
import page.objects.MainPage;
import work.directory.Login;
import work.directory.UserAPI;
import work.directory.WebDriverSetUp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static work.directory.Constants.*;

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
        driver = WebDriverSetUp.runDriver();
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
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        assertNotEquals("Пользователь по-прежнему в личном кабинете", CABINET_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Пользователь не был перенаправлен на страницу авторизации", LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход по кнопке 'Конструктор' из личного Кабинета")
    public void constructorButtonClickInCabinetPageTest() {
        new CabinetPage(driver).constructorButtonClick();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));
        assertEquals(MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход по клику на логотип")
    public void logoClickInCabinetPageTest() {
        new CabinetPage(driver).headerLogoClick();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));
        boolean isLogoAvailable = driver.findElement(By.className("AppHeader_header__logo__2D0X2")).isDisplayed() && driver.findElement(By.className("AppHeader_header__logo__2D0X2")).isEnabled();
        assertNotEquals("Переход не произошёл", CABINET_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Пользователь не был перенаправлен на Главную страницу", MAIN_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Логотип не прогрузился или не кликабелен", true, isLogoAvailable);
    }

    @Test
    @DisplayName("Переход по кнопке 'Личный Кабинет'")
    public void gotoCabinetPageTest() {
        new MainPage(driver).headerLoginButtonClick();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.urlToBe(CABINET_PAGE_URL));

        boolean isCabinetButtonActive = driver.findElement(By.linkText("Личный Кабинет")).isDisplayed() && driver.findElement(By.linkText("Личный Кабинет")).isEnabled();
        assertNotEquals("Переход не произошел", MAIN_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Пользователь не был перенаправлен на страницу личного кабинета", CABINET_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Кнопка Личный кабинет не кликабельна", true, isCabinetButtonActive);
    }

    @After
    public void tearDown() {
        userAPI.deleteUser(accessToken);
        WebDriverSetUp.stopDriver();
    }
}