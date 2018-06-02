package hw1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.setProperty;


public class CheckHomePageAfterLogin {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @AfterMethod
    public void afterMethod() {
        driver.close();

    }

    @Test


    public void checkHomePageAfterLogin() {

        //1. Open test site by URL
        driver.navigate().to("https://epam.github.io/JDI/");

        //2. Assert Browser title
        Assert.assertEquals(driver.getTitle(), "Home Page");

        //3. Perform login
        WebElement userIcon = driver.findElement(By.cssSelector(".profile-photo"));
        userIcon.click();
        driver.findElement(By.cssSelector("#Name")).sendKeys("epam");
        driver.findElement(By.cssSelector("#Password")).sendKeys("1234");
        driver.findElement(By.cssSelector(".form-horizontal button[type = 'submit']")).click();

        //4. Assert User name in the left-top side of screen that user is logged in
        WebElement userName = driver.findElement(By.cssSelector(".profile-photo span"));
        Assert.assertEquals(userName.getText(), "PITER CHAILOVSKII");

        //5. Assert Browser title
        Assert.assertEquals(driver.getTitle(), "Home Page");

        //6. Assert that there are 4 items on the header section are displayed and they have proper texts
        List<String> expectedSections = Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
        List<WebElement> actualSections = driver.findElements(By.cssSelector(".nav > li > a"));
        int expectedSizeHeaderSections = 4;
        for (int i = 0; i < actualSections.size(); i++) {
            Assert.assertEquals(actualSections.get(i).getText(), expectedSections.get(i));
            Assert.assertEquals(actualSections.size(), expectedSizeHeaderSections);
        }

        //7. Assert that there are 4 images on the Home Page and they are displayed
        int expectedSizeImages = 4;
        List<WebElement> actualImages = driver.findElements(By.cssSelector(".benefit-icon"));
        Assert.assertEquals(actualImages.size(), expectedSizeImages);

        //8. Assert that there are 4 texts on the Home Page under icons and they have proper text

        List<String> expectedTexts = Arrays.asList(
                "To include good practices\nand ideas from successful\nEPAM project",
                "To be flexible and\ncustomizable",
                "To be multiplatform",
                "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"
        );
        int expectedSizeTexts = 4;
        List<WebElement> actualTexts = driver.findElements(By.cssSelector(".benefit-txt"));
        for (int i = 0; i < actualTexts.size(); i++) {
            Assert.assertEquals(actualTexts.get(i).getText(), expectedTexts.get(i));
            Assert.assertEquals(actualTexts.size(), expectedSizeTexts);
        }

        //9. Assert a text of the main header
        String EpamText = "EPAM FRAMEWORK WISHES…";
        String LoremText = "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT " +
                "UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI " +
                "UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE" +
                " EU FUGIAT NULLA PARIATUR.";

        //Assert "EPAM FRAMEWORK WISHES..." text
        WebElement mainTitle = driver.findElement(By.cssSelector("h3[name=main-title]"));
        Assert.assertTrue(mainTitle.isDisplayed());
        Assert.assertEquals(mainTitle.getText(), EpamText);

        //Assert "LOREM IPSUM" text
        WebElement jdiText = driver.findElement(By.cssSelector("p[name=jdi-text]"));
        Assert.assertTrue(jdiText.isDisplayed());
        Assert.assertEquals(jdiText.getText(), LoremText);

        //10. Assert a text of the sub header
        String expectedSubHeaderText = "JDI GITHUB";
        WebElement subHeaderElement = driver.findElement(By.xpath("//a[text()=\"JDI Github\"]"));
        Assert.assertTrue(subHeaderElement.isDisplayed());
        Assert.assertEquals(subHeaderElement.getText(), expectedSubHeaderText);

        //11. Assert that JDI GITHUB is a link and has a proper URL
        String expectedURL = "https://github.com/epam/JDI";
        Assert.assertEquals(subHeaderElement.getAttribute("href"), expectedURL);

        //12. Assert that there is Left Section
        WebElement leftSection = driver.findElement(By.cssSelector("div[name=navigation-sidebar]"));
        Assert.assertTrue(leftSection.isDisplayed());

        //13. Assert that there is Footer
        WebElement footer = driver.findElement(By.cssSelector(".footer-bg"));
        Assert.assertTrue(footer.isDisplayed());
    }
}



