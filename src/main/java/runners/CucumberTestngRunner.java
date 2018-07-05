package runners;

import com.codeborne.selenide.Configuration;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.close;

@CucumberOptions(
//        features = "src/test/java/hw6/CheckHomePageAndDatesPageUsingSelenideCucumber.feature",
        features = {"src/test/java/hw6/CheckHomePageAndDatesPageUsingSelenideCucumber.feature", "src/test/java/hw6/CheckHomePageAndUserTablePageUsingSelenideCucumber.feature"},
        glue = {"pageObjects.cucumber"})
public class CucumberTestngRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void beforeSuite() {
        Configuration.browser = "chrome";
    }
    @AfterMethod
    public void afterMethod() {
        close();
    }
}