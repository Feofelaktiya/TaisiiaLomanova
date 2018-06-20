package hw4;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.AllureAttachmentListener;
import org.testng.annotations.*;
import pageObjects.selenide.DifferentElementsPageSelenide;
import pageObjects.selenide.HomePageSelenide;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.page;
import static enums.Texts.checkboxTexts.CHECKBOX_TEXTS;
import static enums.Texts.dropdownColors.DROPDOWN_COLORS;
import static enums.Texts.radiobuttonTexts.RADIOBUTTON_TEXTS;
import static enums.Texts.serviceOptions.SERVICE_OPTIONS;
import static enums.Texts.webElementTypes.WEB_ELEMENT_TYPES;
import static enums.Users.PITER_CHAILOVSKII;

@Feature("Different Page")
@Story("Checkboxes/radiobuttons choice and dropdown selection  on Different Elements page are logged in the Log list")
@Listeners(AllureAttachmentListener.class)
@Test(groups = "hw4")

public class CheckHomePageAndDifferentElementsPageUsingSelenide {
    private HomePageSelenide homepage;
    private DifferentElementsPageSelenide diffElementsPage;

    @BeforeSuite

    public void beforeSuite() {
        Configuration.browser = "chrome";
    }

    @BeforeClass
    public void beforeClass() {
        homepage = page(HomePageSelenide.class);
        diffElementsPage = page(DifferentElementsPageSelenide.class);
    }

    @AfterClass
    public void afterClass() {
        close();
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

        //5 Check interface on Home page, it contains all needed elements
        homepage.checkElementsOnHomePage();

        //6 Click on "Service" subcategory in the header and check that drop down contains options
        homepage.checkDropdownOptions();

        //7 Click on Service subcategory in the left section and check that drop down contains options
        homepage.checkLeftSectionDropdownOptions();

        //8 Open through the header menu Service -> Different Elements Page
        homepage.openPage(SERVICE_OPTIONS.differentElements);

        //9 Check interface on Different elements page, it contains all needed elements	4 checkboxes, 4 radios, 1 dropdown, 2 buttons
        diffElementsPage.checkElementsOnDifferentElementsPage();

        //10 Assert that there is Right Section
        diffElementsPage.checkRightSectionIsDisplayed();

        //11 Assert that there is Left Section
        diffElementsPage.checkLeftSectionIsDisplayed();

        //12 Select checkboxes
        diffElementsPage.selectWebElement(WEB_ELEMENT_TYPES.chbx, CHECKBOX_TEXTS.water);
        diffElementsPage.selectWebElement(WEB_ELEMENT_TYPES.chbx, CHECKBOX_TEXTS.wind);

        //13 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox.
        diffElementsPage.checkLogForCheckboxex(1, CHECKBOX_TEXTS.wind, true);
        diffElementsPage.checkLogForCheckboxex(2, CHECKBOX_TEXTS.water, true);

        //14 Select radio
        diffElementsPage.selectWebElement(WEB_ELEMENT_TYPES.radio, RADIOBUTTON_TEXTS.selen);

        //15 Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton. 
        // diffElementsPage.checkLogForRadiobuttons(3, RADIOBUTTON_TEXTS.selen);
        diffElementsPage.checkLogForRadiobuttons(1, RADIOBUTTON_TEXTS.selen);

        //16 Select in dropdown
        diffElementsPage.selectWebElement(WEB_ELEMENT_TYPES.ddown, DROPDOWN_COLORS.yellow);

        //17 Assert that for dropdown there is a log row and value is corresponded to the selected value. 
        // diffElementsPage.checkLogForDropdown(4, DROPDOWN_COLORS.yellow);
        diffElementsPage.checkLogForDropdown(1, DROPDOWN_COLORS.yellow);

        //18 Unselect and assert checkboxes
        diffElementsPage.selectWebElement(WEB_ELEMENT_TYPES.chbx, CHECKBOX_TEXTS.water);
        diffElementsPage.selectWebElement(WEB_ELEMENT_TYPES.chbx, CHECKBOX_TEXTS.wind);

        //19 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox. 
        // diffElementsPage.checkLogForCheckboxex(CHECKBOX_TEXTS.water, 6, false);
        diffElementsPage.checkLogForCheckboxex(2, CHECKBOX_TEXTS.water, false);
        diffElementsPage.checkLogForCheckboxex(1, CHECKBOX_TEXTS.wind, false);

    }
}
