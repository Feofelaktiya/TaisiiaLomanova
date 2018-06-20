package hw2.ex1;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.System.setProperty;

@Feature("Home Page")
@Story("Home Page after login contains certain elements")
public class CheckHomePageTextUsingDataProvider {
    @BeforeClass
    public void beforeClass() {
        setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
    }


    //Create data provider with attribute "parallel" - true and following variables: [homePageBenefit, homePageText]
    @DataProvider(parallel = true)
    public Object[][] textUnderImages() {
        return new Object[][]{
                {"\"To include good practices\"", "To include good practices\nand ideas from successful\nEPAM project"},
                {"\"To be flexible and\"", "To be flexible and\ncustomizable"},
                {"\"To be multiplatform \"", "To be multiplatform"},
                {"\"Already have good base\"", "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"}};
    }

    //Use Data Provider for test
    @Test(dataProvider = "textUnderImages")

    public void checkTextUnderImages(String homePageBenefit, String homePageText) {
        //1. Create new Chrome instance and navigate to required URL
        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://epam.github.io/JDI/");

        //2. Check items from Data Provider are displayed on page and contain required text
        WebElement itemOnPage = driver.findElement(By.xpath("//span[text()=" + homePageBenefit + "]"));
        Assert.assertTrue(itemOnPage.isDisplayed());
        Assert.assertEquals(itemOnPage.getText(), homePageText);

        //3. Close Chrome instance
        driver.close();

    }

}


