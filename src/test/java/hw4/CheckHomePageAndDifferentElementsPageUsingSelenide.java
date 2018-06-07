package hw4;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pageObjects.HomePageSelenide;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.page;
import static enums.Texts.checkboxTexts.CHECKBOX_TEXTS;
import static enums.Texts.dropdownColors.DROPDOWN_COLORS;
import static enums.Texts.radiobuttonTexts.RADIOBUTTON_TEXTS;
import static enums.Texts.webElementTypes.WEB_ELEMENT_TYPES;
import static enums.Users.PITER_CHAILOVSKII;

public class CheckHomePageAndDifferentElementsPageUsingSelenide {
    HomePageSelenide homepage;

    @BeforeSuite

    public void beforeSuite() {
        Configuration.browser = "chrome";
    }

    @BeforeClass
    public void beforeClass() {
        homepage = page(HomePageSelenide.class);
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
        homepage.openDifferentElementsPage();

        //9 Check interface on Different elements page, it contains all needed elements	4 checkboxes, 4 radios, 1 dropdown, 2 buttons
        homepage.checkElementsOnDifferentElementsPage();

        //10 Assert that there is Right Section
        homepage.checkRightSectionIsDisplayed();

        //11 Assert that there is Left Section
        homepage.checkLeftSectionIsDisplayed();

        //12 Select checkboxes
        homepage.selectWebElement(WEB_ELEMENT_TYPES.chbx, CHECKBOX_TEXTS.water);
        homepage.selectWebElement(WEB_ELEMENT_TYPES.chbx, CHECKBOX_TEXTS.wind);

        //13 Assert that for each checkbox there is an individual log row and value is
//         corresponded to the status of checkbox. 

        homepage.checkLogForCheckboxex(CHECKBOX_TEXTS.water, 2, true);
        homepage.checkLogForCheckboxex(CHECKBOX_TEXTS.wind, 2, true);

        //14 Select radio

        homepage.selectWebElement(WEB_ELEMENT_TYPES.radio, RADIOBUTTON_TEXTS.selen);

        //15 Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton. 
        homepage.checkLogForRadiobuttons(3,RADIOBUTTON_TEXTS.selen);

        //16 Select in dropdown
        homepage.selectWebElement(WEB_ELEMENT_TYPES.ddown, DROPDOWN_COLORS.yellow);

        //17 Assert that for dropdown there is a log row and value is corresponded to the selected value. 
        homepage.checkLogForDropdown(4, DROPDOWN_COLORS.yellow);

        //18 Unselect and assert checkboxes
        homepage.selectWebElement(WEB_ELEMENT_TYPES.chbx, CHECKBOX_TEXTS.water);
        homepage.selectWebElement(WEB_ELEMENT_TYPES.chbx, CHECKBOX_TEXTS.wind);

        //19 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox. 
        homepage.checkLogForCheckboxex(CHECKBOX_TEXTS.water, 6, false);
        homepage.checkLogForCheckboxex(CHECKBOX_TEXTS.wind, 6, false);

        close();
    }


}
