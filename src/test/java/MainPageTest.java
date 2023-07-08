import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.objects.MainPage;
import work.directory.Constants;
import work.directory.WebDriverSetUp;
import static org.junit.Assert.assertEquals;

public class MainPageTest extends TestRandomizer{

    private WebDriver driver;
    private MainPage mainPage;
    private String expectedEntity;

    @Before
    public void setUp() {
        driver = WebDriverSetUp.runDriver();
        driver.get(Constants.MAIN_PAGE_URL);
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Переход на вкладку 'Булки'")
    public void entityBunsButtonClickTest() {
        expectedEntity = "Булки";
        if (mainPage.getEntityActiveButton().equals("Булки")) {
            mainPage.entityFillingsButtonClick();
        }
        mainPage.entityBunsButtonClick();
        assertEquals(expectedEntity, mainPage.getEntityActiveButton());
    }

    @Test
    @DisplayName("Переход на вкладку 'Соусы'")
    public void entitySaucesButtonClickTest() {
        expectedEntity = "Соусы";
        if(mainPage.getEntityActiveButton().equals("Соусы")) {
            mainPage.entityFillingsButtonClick();
        }
        mainPage.entitySaucesButtonClick();
        assertEquals(expectedEntity, mainPage.getEntityActiveButton());
    }

    @Test
    @DisplayName("Переход на вкладку 'Начинки'")
    public void entityFillingsButtonClickTest() {
        expectedEntity = "Начинки";
        if (mainPage.getEntityActiveButton().equals("Начинки")) {
            mainPage.entitySaucesButtonClick();
        }
        mainPage.entityFillingsButtonClick();
        assertEquals(expectedEntity, mainPage.getEntityActiveButton());
    }

    @After
    public void tearDown() {
        WebDriverSetUp.stopDriver();
    }
}