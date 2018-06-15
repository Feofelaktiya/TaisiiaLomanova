package hw4;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pageObjects.selenide.DataPageSelenide;
import pageObjects.selenide.HomePageSelenide;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.page;
import static enums.Texts.rangeLogs.RANGE_LOGS;
import static enums.Texts.serviceOptions.SERVICE_OPTIONS;
import static enums.Users.PITER_CHAILOVSKII;

@Test
public class CheckHomePageAndDatesPageUsingSelenide {
    private HomePageSelenide homepage;
    private DataPageSelenide datapage;

    @BeforeSuite
    public void beforeSuite() {
        Configuration.browser = "chrome";
    }

    @BeforeClass
    public void beforeClass() {
        homepage = page(HomePageSelenide.class);
        datapage = page(DataPageSelenide.class);
    }

    @Test
    public void testCaseOne() {
        //1 Open test site by URL
        homepage.open();

        //2 Assert Browser title
        homepage.checkBrowserTitleIsDisplayed();

        //3 Perform login
        homepage.login(PITER_CHAILOVSKII.login, PITER_CHAILOVSKII.password);

        //4 Assert User name in the left-top side of screen that user is loggined
        homepage.checkUserName();

        //5 Open through the header menu Service -> Dates Page
        homepage.openPage(SERVICE_OPTIONS.dates);

        //6 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most rigth position
        datapage.moveLeftPoint("20", 0);
        datapage.moveRightPoint("100", 100);

        //7 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datapage.checkLogsForRange(2, RANGE_LOGS.from, 0);
        datapage.checkLogsForRange(1, RANGE_LOGS.to, 100);

        //8 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most left position.
        datapage.moveLeftPoint("0", 0);
        datapage.moveRightPoint("100", 0);

        //9 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datapage.checkLogsForRange(2, RANGE_LOGS.from, 0);
        datapage.checkLogsForRange(1, RANGE_LOGS.to, 0);

        //10 Using drag-and-drop set Range sliders. left sliders - the most rigth position, right slider - the most rigth position.
        datapage.moveRightPoint("0", 100);
        datapage.moveLeftPoint("0", 100);

        //11 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datapage.checkLogsForRange(1, RANGE_LOGS.from, 100);
        datapage.checkLogsForRange(2, RANGE_LOGS.to, 100);

        //12 Using drag-and-drop set Range sliders.
        datapage.moveLeftPoint("100", 30);
        datapage.moveRightPoint("100", 70);

        //13 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datapage.checkLogsForRange(1, RANGE_LOGS.from, 30);
        datapage.checkLogsForRange(2, RANGE_LOGS.to, 70);

        //14 Closing browser
        close();

    }


}
