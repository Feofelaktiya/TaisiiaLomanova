package pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;
import static enums.Texts.aboveTexts.ABOVE_TEXTS;
import static enums.Texts.benefitTexts.BENEFIT_TEXTS;
import static enums.Texts.checkboxTexts.CHECKBOX_TEXTS;
import static enums.Texts.radiobuttonTexts.RADIOBUTTON_TEXTS;
import static enums.Texts.sectionTitles.SECTION_TITLES;
import static enums.Texts.serviceOptions.SERVICE_OPTIONS;
import static enums.Texts.webElementTypes.WEB_ELEMENT_TYPES;
import static enums.Users.PITER_CHAILOVSKII;

public class HomePageSelenide {
        @FindBy(css = ".profile-photo")

    private SelenideElement userIcon;

    @FindBy(css = "#Name")
    private SelenideElement loginInput;

    @FindBy(css = "#Password")
    private SelenideElement passwordInput;

    @FindBy(css = ".form-horizontal button[type = 'submit']")
    private SelenideElement submitButton;

    @FindBy(css = ".profile-photo span")
    private SelenideElement userName;

    @FindBy(css = ".nav > li > a")
    private ElementsCollection actualSections;

    @FindBy(css = ".benefit-icon")
    private ElementsCollection actualImages;

    @FindBy(css = ".benefit-txt")
    private ElementsCollection actualTexts;

    @FindBy(css = "h3[name=main-title]")
    private SelenideElement mainTitle;

    @FindBy(css = "p[name=jdi-text]")
    private SelenideElement jdiText;

    @FindBy(xpath = "//a[text()=\"JDI Github\"]")
    private SelenideElement subHeaderElement;

    @FindBy(css = "div[name=navigation-sidebar]")
    private SelenideElement navigationSidebar;

    @FindBy(css = ".footer-bg")
    private SelenideElement footer;

    @FindBy(css = ".dropdown-toggle")
    private SelenideElement dropdownName;

    @FindBy(css = ".dropdown-menu > li")
    private ElementsCollection dropdownOptions;

    @FindBy(css = "p > span")
    private ElementsCollection leftSectionElements;

    @FindBy(css = ".label-checkbox")
    private ElementsCollection labelCheckboxes;

    @FindBy(css = ".label-radio")
    private ElementsCollection labelRadiobuttons;

    @FindBy(css = "select.uui-form-element > option")
    private ElementsCollection colorDropdown;

    @FindBy(css = "select.uui-form-element")
    private SelenideElement selectedColorField;

    @FindBy(css = "button.uui-button")
    private SelenideElement defaultButton;

    @FindBy(css = "input.uui-button")
    private SelenideElement button;

    @FindBy(css = "div[name=log-sidebar]")
    private SelenideElement rightSection;

    @FindBy(css = "div[name=navigation-sidebar]")
    private SelenideElement leftSection;

    @FindBy(css = "ul.panel-body-list.logs > li")
    private ElementsCollection logs;


    public void open() {
        Selenide.open("https://epam.github.io/JDI/");

    }

    public void checkBrowserTitleIsDisplayed() {
        $(byTitle("Home Page")).isDisplayed();
    }

    public void login(String name, String password) {
        $(userIcon).click();
        $(loginInput).sendKeys(name);
        passwordInput.sendKeys(password);
        submitButton.click();
    }

    public void checkUserName() {
        userName.isDisplayed();
        userName.shouldHave(text(PITER_CHAILOVSKII.name));
    }

    public void checkElementsOnHomePage() {
        actualImages.shouldHaveSize(4);
        actualTexts.shouldBe(texts(BENEFIT_TEXTS.benefitOne,
                BENEFIT_TEXTS.benefitTwo, BENEFIT_TEXTS.benefitThree, BENEFIT_TEXTS.benefitFour));
        mainTitle.shouldHave(text(ABOVE_TEXTS.epam));
        jdiText.shouldHave(text(ABOVE_TEXTS.lorem));
    }

    public void checkDropdownOptions() {
        dropdownName.shouldHave(text(SECTION_TITLES.service)).click();
        dropdownOptions.shouldHaveSize(8);
        dropdownOptions.shouldBe(texts(SERVICE_OPTIONS.support,
                SERVICE_OPTIONS.dates, SERVICE_OPTIONS.complexTable, SERVICE_OPTIONS.simpleTable,
                SERVICE_OPTIONS.userTable, SERVICE_OPTIONS.tableWithWages,
                SERVICE_OPTIONS.differentElements, SERVICE_OPTIONS.performance));
    }

    public void checkLeftSectionDropdownOptions() {
        leftSectionElements.shouldHaveSize(8);
        leftSectionElements.shouldHave(texts(SERVICE_OPTIONS.support,
                SERVICE_OPTIONS.dates, SERVICE_OPTIONS.complexTable, SERVICE_OPTIONS.simpleTable,
                SERVICE_OPTIONS.userTable, SERVICE_OPTIONS.tableWithWages,
                SERVICE_OPTIONS.differentElements, SERVICE_OPTIONS.performance));

    }

    public void openDifferentElementsPage() {
        leftSectionElements.findBy(text(SERVICE_OPTIONS.differentElements)).click();
        $(byTitle("Different Element")).isDisplayed();

    }

    public void checkElementsOnDifferentElementsPage() {
        labelCheckboxes.shouldHave(texts(CHECKBOX_TEXTS.water, CHECKBOX_TEXTS.earth, CHECKBOX_TEXTS.wind, CHECKBOX_TEXTS.fire));
        labelRadiobuttons.shouldHave(texts(RADIOBUTTON_TEXTS.gold, RADIOBUTTON_TEXTS.silver, RADIOBUTTON_TEXTS.bronze, RADIOBUTTON_TEXTS.selen));
        selectedColorField.isDisplayed();
        defaultButton.isDisplayed();
        button.isDisplayed();
    }

    public void checkRightSectionIsDisplayed() {
        rightSection.isDisplayed();
    }

    public void checkLeftSectionIsDisplayed() {
        leftSection.isDisplayed();
    }

    public void selectWebElement(String webElementType, String type) {
        if (webElementType.equals(WEB_ELEMENT_TYPES.chbx)) {
            labelCheckboxes.findBy(text(type)).click();
        }
        if (webElementType.equals(WEB_ELEMENT_TYPES.radio)) {
            labelRadiobuttons.findBy(text(type)).click();
        }
        if (webElementType.equals(WEB_ELEMENT_TYPES.ddown)) {
            selectedColorField.click();
            colorDropdown.findBy(text(type)).click();
            selectedColorField.shouldHave(text(type));
        }
    }

    public void checkLogForCheckboxex(String type, int logsAmount, boolean checkedStatus) {
        logs.shouldHaveSize(logsAmount);
        String actual = logs.findBy(text(type)).text();
        String[] parts = actual.split(" ", 2);
        String numbers = parts[0];
        String characters = parts[1];
        String expected = type + ": condition changed to " + checkedStatus;
        Assert.assertEquals(characters, expected);
    }

    public void checkLogForRadiobuttons(int logsAmount, String metal) {
        logs.shouldHaveSize(logsAmount);
        String actual = logs.findBy(text(metal)).text();
        String[] parts = actual.split(" ", 2);
        String numbers = parts[0];
        String characters = parts[1];
        String expected = "metal: value changed to " + metal;
        Assert.assertEquals(characters, expected);
    }

    public void checkLogForDropdown(int logAmount, String color) {
        logs.shouldHaveSize(logAmount);
        String actual = logs.findBy(text(color)).text();
        String[] parts = actual.split(" ", 2);
        String numbers = parts[0];
        String characters = parts[1];
        String expected = "Colors: value changed to " + color;
        Assert.assertEquals(characters, expected);
    }
}

