package hw3;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import pageObjects.HomePage;

import static java.lang.System.setProperty;

@Feature("Home Page")
@Story("Home Page after login contains certain elements")
@Test(groups = {"Regression"})
public class CheckHomePageAfterLoginWithPageObject {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeSuite
    public void beforeSuite() {
        setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
    }

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println(driver.getTitle());
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void checkHomePageAfterLogin() {

        //1. Open test site by URL
        homePage.open(driver);

        //2. Assert Browser title
        homePage.checkHomePageTitle(driver);

        //3. Perform login
        homePage.login("epam", "1234");

        //4. Assert User name in the left-top side of screen that user is logged in
        homePage.checkUserName();

        //5. Assert Browser title
        homePage.checkHomePageTitle(driver);

        //6. Assert that there are 4 items on the header section are displayed and they have proper texts
        homePage.checkPageContainsElements();

        //7. Assert that there are 4 images on the Home Page and they are displayed
        homePage.checkPageContainsImages();

        //8. Assert that there are 4 texts on the Home Page under icons and they have proper text
        homePage.checkPageContainsTexts();

        //9. Assert a text of the main header
        homePage.checkPageContainsSpecialTexts();

        //10. Assert a text of the sub header
        homePage.checkTTextOfTheSubHeader();

        //11. Assert that JDI GITHUB is a link and has a proper URL
        homePage.checkJDILinkURL();

        //12. Assert that there is Left Section
        homePage.checkPageContainsNavigationSection();

        //13. Assert that there is Footer
        homePage.checkPageContainsFooter();
    }
}



