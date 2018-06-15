package hw2.ex2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static java.lang.System.setProperty;

@Test(groups = {"Regression"})
public class SimpleTestsForSmokeAndRegressionRuns {

    @BeforeClass
    public void beforeClass() {
        setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
    }

    @Test(groups = {"Regression"})
    public void simpleSeleniumTest() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download_default_directory", "target");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("pref", chromePrefs);

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://jdi-framework.github.io/tests/index.htm");

        Assert.assertEquals(driver.getTitle(), "Index Page");

        WebElement userIcon = driver.findElement(By.cssSelector(".fa-user"));
        userIcon.click();
        driver.findElement(By.cssSelector("#Login")).sendKeys("epam");
        driver.findElement(By.cssSelector("#Password")).sendKeys("1234");
        driver.findElement(By.cssSelector(".form-horizontal button[type = 'submit']")).click();
        WebElement userName = driver.findElement(By.cssSelector(".profile-photo span"));
        Assert.assertEquals(userName.getText(), "PITER CHAILOVSKII");

        driver.close();

    }

    @Test(groups = {"Regression"})
    public void simpleSeleniumTest2() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download_default_directory", "target");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("pref", chromePrefs);

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://jdi-framework.github.io/tests/index.htm");

        Assert.assertEquals(driver.getTitle(), "Index Page");

        WebElement userIcon = driver.findElement(By.cssSelector(".fa-user"));
        userIcon.click();
        driver.findElement(By.cssSelector("#Login")).sendKeys("epam");
        driver.findElement(By.cssSelector("#Password")).sendKeys("1234");
        driver.findElement(By.cssSelector(".form-horizontal button[type = 'submit']")).click();
        WebElement userName = driver.findElement(By.cssSelector(".profile-photo span"));
        Assert.assertEquals(userName.getText(), "PITER CHAILOVSKII");

        driver.close();

    }

    @Test(groups = {"Smoke"})
    public void simpleSeleniumTest3() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download_default_directory", "target");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("pref", chromePrefs);

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://jdi-framework.github.io/tests/index.htm");

        Assert.assertEquals(driver.getTitle(), "Index Page");

        WebElement userIcon = driver.findElement(By.cssSelector(".fa-user"));
        userIcon.click();
        driver.findElement(By.cssSelector("#Login")).sendKeys("epam");
        driver.findElement(By.cssSelector("#Password")).sendKeys("1234");
        driver.findElement(By.cssSelector(".form-horizontal button[type = 'submit']")).click();
        WebElement userName = driver.findElement(By.cssSelector(".profile-photo span"));
        Assert.assertEquals(userName.getText(), "PITER CHAILOVSKII");

        driver.close();

    }


}
