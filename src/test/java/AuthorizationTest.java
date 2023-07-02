import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page_objects.AuthorizationPage;
import page_objects.MainPage;
import page_objects.PasswordPage;
import page_objects.RegistrationPage;
import work_directory.Constants;
import work_directory.Login;
import work_directory.UserAPI;

import static org.junit.Assert.assertEquals;
import static work_directory.Constants.LOGIN_PAGE_URL;

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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
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
        MainPage mainPage = new MainPage(driver);
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        mainPage.headerLoginButtonClick();
        authorizationPage.authorization(email, password);
        assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
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
        new PasswordPage(driver).loginButtonClick();
        new AuthorizationPage(driver).authorization(email, password);
        assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        userAPI.deleteUser(accessToken);
        driver.quit();
    }
}