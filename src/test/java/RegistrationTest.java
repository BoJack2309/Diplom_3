import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page_objects.RegistrationPage;
import work_directory.Constants;
import work_directory.Login;
import work_directory.UserAPI;

import static org.apache.http.util.TextUtils.isEmpty;
import static org.junit.Assert.assertEquals;
import static work_directory.Constants.LOGIN_PAGE_URL;
import static work_directory.Constants.REGISTRATION_PAGE_URL;

public class RegistrationTest {

    private WebDriver driver;
    private String email;
    private String password;
    private String correctPassword;

    private String incorrectPassword;
    private String name;
    private UserAPI userAPI;
    private Login login;
    private String accessToken;

    @Before
    public void setUp() {
        email = TestRandomizer.Email;
        correctPassword = TestRandomizer.correctPassword;
        incorrectPassword = TestRandomizer.incorrectPassword;
        name = TestRandomizer.Name;
        userAPI = new UserAPI();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        driver.get(REGISTRATION_PAGE_URL);
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void registrationTest() {
        password = correctPassword;
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(email, password, name);
        assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Регистрация с некорректным паролем")
    public void registrationWithIncorrectPasswordTest() {
        password = incorrectPassword;
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(email, password, name);
        registrationPage.incorrectPasswordError();
        assertEquals(REGISTRATION_PAGE_URL, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        login = new Login(email, password);
        ValidatableResponse userLoginRequest = userAPI.registerUser(login);
        this.accessToken = userLoginRequest.extract().path("accessToken");
        if (!isEmpty(accessToken)) {userAPI.deleteUser(accessToken);}
        driver.quit();
    }
}