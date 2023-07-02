import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page_objects.MainPage;
import work_directory.Constants;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainPageTest extends TestRandomizer{

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);

        driver.get(Constants.MAIN_PAGE_URL);
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Переход на вкладку 'Булки'")
    public void entityBunsButtonClickTest() {
        if (mainPage.getEntityActiveButton().equals("Булки")) {
            mainPage.entitySaucesButtonClick();
        }
        mainPage.entityBunsButtonClick();
        assertThat(true, equalTo(driver.findElement(By.xpath(".//div/span[text()='Булки']")).isDisplayed()));
    }

    @Test
    @DisplayName("Переход на вкладку 'Соусы'")
    public void entitySaucesButtonClickTest() {
        if(mainPage.getEntityActiveButton().equals("Соусы")) {
            mainPage.entityFillingsButtonClick();
        }
        mainPage.entitySaucesButtonClick();
        assertThat(true, equalTo(driver.findElement(By.xpath(".//div/span[text()='Соусы']")).isDisplayed()));
    }

    @Test
    @DisplayName("Переход на вкладку 'Начинки'")
    public void entityFillingsButtonClickTest() {
        if (mainPage.getEntityActiveButton().equals("Начинки")) {
            mainPage.entitySaucesButtonClick();
        }
        mainPage.entityFillingsButtonClick();
        assertThat(true, equalTo(driver.findElement(By.xpath(".//div/span[text()='Начинки']")).isDisplayed()));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}