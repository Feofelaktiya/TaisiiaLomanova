package hw1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class CheckHomePageAfterLogin {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
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
        ArrayList<String> items = new ArrayList<>();
        items.add("Home");
        items.add("Contact form");
        items.add("Service");
        items.add("Metals & Colors");

        for (String headerItems : items) {
            WebElement tabs = driver.findElement(By.xpath("//header//a[normalize-space()='" + headerItems + "']"));
            Assert.assertTrue(tabs.isDisplayed());
            Assert.assertEquals(tabs.getText(), headerItems.toUpperCase());
        }


        //7. Assert that there are 4 images on the Home Page and they are displayed
        Integer requiredSize = 4;
        Integer size = driver.findElements(By.cssSelector(".benefit-icon")).size();
        Assert.assertTrue(size.equals(requiredSize));

        //8. Assert that there are 4 texts on the Home Page under icons and they have proper text
        class Benefit {
            String homePageTexts;
            String homePageBenefit;

            Benefit(String v1, String v2) {
                homePageBenefit = v1;
                homePageTexts = v2;
            }

            public String getHomePageTexts() {
                return homePageTexts;
            }

            public String getHomePageBenefit() {
                return homePageBenefit;
            }
        }
        List<Benefit> homePageBenefits = new ArrayList<Benefit>();
        Benefit b1 = new Benefit("\"To include good practices\"", "To include good practices\nand ideas from successful\nEPAM project");
        Benefit b2 = new Benefit("\"To be flexible and\"", "To be flexible and\ncustomizable");
        Benefit b3 = new Benefit("\"To be multiplatform \"", "To be multiplatform");
        Benefit b4 = new Benefit("\"Already have good base\"", "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");
        homePageBenefits.add(b1);
        homePageBenefits.add(b2);
        homePageBenefits.add(b3);
        homePageBenefits.add(b4);

        for (Benefit benefits : homePageBenefits
                ) {
            WebElement itemOnPage = driver.findElement(By.xpath("//span[text()=" + benefits.getHomePageBenefit() + "]"));
            Assert.assertTrue(itemOnPage.isDisplayed());
            Assert.assertEquals(itemOnPage.getText(), benefits.getHomePageTexts());
        }

        //9. Assert a text of the main header
        String EpamText = "EPAM FRAMEWORK WISHES…";
        String LoremText = "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.";

        //Assert "EPAM FRAMEWORK WISHES..." text
        WebElement element = driver.findElement(By.xpath("//h3[@name=\"main-title\"]"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertEquals(element.getText(), EpamText);

        //Assert "LOREM IPSUM" text
        WebElement anotherElement = driver.findElement(By.xpath("//p[@name=\"jdi-text\"]"));
        Assert.assertTrue(anotherElement.isDisplayed());
        Assert.assertEquals(anotherElement.getText(), LoremText);

        //10. Assert a text of the sub header
        String subHeaderText = "JDI GITHUB";
        WebElement subHeaderElement = driver.findElement(By.xpath("//a[text()=\"JDI Github\"]"));
        Assert.assertTrue(subHeaderElement.isDisplayed());
        Assert.assertEquals(subHeaderElement.getText(), subHeaderText);

        //11. Assert that JDI GITHUB is a link and has a proper URL
        String properURL = "https://github.com/epam/JDI";
        Assert.assertEquals(subHeaderElement.getAttribute("href"), properURL);

        //12. Assert that there is Left Section
        WebElement leftSection = driver.findElement(By.xpath("//div[@name='navigation-sidebar']"));
        Assert.assertTrue(leftSection.isDisplayed());

        //13. Assert that there is Footer
        WebElement footer = driver.findElement(By.xpath("//div[@class='footer-bg']"));
        Assert.assertTrue(footer.isDisplayed());
    }
}



