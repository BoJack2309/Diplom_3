import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.objects.AuthorizationPage;
import page.objects.MainPage;
import page.objects.PasswordPage;
import page.objects.RegistrationPage;
import work.directory.Constants;
import work.directory.Login;
import work.directory.UserAPI;
import work.directory.WebDriverSetUp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static work.directory.Constants.*;

public class AuthorizationTest extends TestRandomizer{

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
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт'")
    public void mainPageLoginButtonTest() {
        driver.get(Constants.MAIN_PAGE_URL);
        MainPage mainPage = new MainPage(driver);
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        mainPage.loginButtonClick();
        authorizationPage.authorization(email, password);
        assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход по кнопке 'Личный кабинет'")
    public void headerLoginButtonTest() {
        driver.get(Constants.MAIN_PAGE_URL);
        boolean isCabinetButtonActive = driver.findElement(By.linkText("Личный Кабинет")).isDisplayed() && driver.findElement(By.linkText("Личный Кабинет")).isEnabled();
        MainPage mainPage = new MainPage(driver);
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        mainPage.headerLoginButtonClick();
        authorizationPage.authorization(email, password);
        assertNotEquals("Переход не произошел", MAIN_PAGE_URL, driver.getCurrentUrl());
        assertEquals("URL отличается от указанного", LOGIN_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Кнопка Войти не кликабельна", true, isCabinetButtonActive);
    }

    @Test
    @DisplayName("Вход по кнопке в форме регистрации")
    public void registrationLoginButtonTest() {
        driver.get(Constants.REGISTRATION_PAGE_URL);
        new RegistrationPage(driver).loginButtonClick();
        new AuthorizationPage(driver).authorization(email, password);
        assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход по кнопке в форме восстановления пароля")
    public void passwordRecoveryButtonTest() {
        driver.get(Constants.PASSWORD_PAGE_URL);
        boolean isLoginButtonAvailable = driver.findElement(By.linkText("Войти")).isDisplayed() && driver.findElement(By.linkText("Войти")).isEnabled();
        new PasswordPage(driver).loginButtonClick();
        new AuthorizationPage(driver).authorization(email, password);
        assertEquals("Пользователь не был перенаправлен", LOGIN_PAGE_URL, driver.getCurrentUrl());
        assertNotEquals("Переход не произошел", PASSWORD_PAGE_URL, driver.getCurrentUrl());
        assertEquals("Кнопка Войти не кликабельна", true, isLoginButtonAvailable);
    }

    @After
    public void tearDown() {
        userAPI.deleteUser(accessToken);
        WebDriverSetUp.stopDriver();
    }
}