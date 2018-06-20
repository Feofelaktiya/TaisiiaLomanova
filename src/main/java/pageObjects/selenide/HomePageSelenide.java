package pageObjects.selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;
import static enums.Texts.aboveTexts.ABOVE_TEXTS;
import static enums.Texts.benefitTexts.BENEFIT_TEXTS;
import static enums.Texts.sectionTitles.SECTION_TITLES;
import static enums.Texts.serviceOptions.SERVICE_OPTIONS;
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

    @Step("Open browser")
    public void open() {
        Selenide.open("https://epam.github.io/JDI/");

    }

    @Step("Check home page title")
    public void checkBrowserTitleIsDisplayed() {
        $(byTitle("Home Page")).isDisplayed();
    }

    @Step("Login to the web application")
    public void login(String name, String password) {
        $(userIcon).click();
        $(loginInput).sendKeys(name);
        passwordInput.sendKeys(password);
        submitButton.click();
    }

    @Step("Check that login is successful by checking user name")
    public void checkUserName() {
        userName.isDisplayed();
        userName.shouldHave(text(PITER_CHAILOVSKII.name));
    }

    @Step("Check elements on the home page")
    public void checkElementsOnHomePage() {
        actualImages.shouldHaveSize(4);
        actualTexts.shouldBe(texts(BENEFIT_TEXTS.benefitOne,
                BENEFIT_TEXTS.benefitTwo, BENEFIT_TEXTS.benefitThree, BENEFIT_TEXTS.benefitFour));
        mainTitle.shouldHave(text(ABOVE_TEXTS.epam));
        jdiText.shouldHave(text(ABOVE_TEXTS.lorem));
    }

    @Step("Check dropdown options on the home page")
    public void checkDropdownOptions() {
        dropdownName.shouldHave(text(SECTION_TITLES.service)).click();
        dropdownOptions.shouldHaveSize(8);
        dropdownOptions.shouldBe(texts(SERVICE_OPTIONS.support,
                SERVICE_OPTIONS.dates, SERVICE_OPTIONS.complexTable, SERVICE_OPTIONS.simpleTable,
                SERVICE_OPTIONS.userTable, SERVICE_OPTIONS.tableWithWages,
                SERVICE_OPTIONS.differentElements, SERVICE_OPTIONS.performance));
    }

    @Step("Check navigation section dropdown options on the home page")
    public void checkLeftSectionDropdownOptions() {
        leftSectionElements.shouldHaveSize(8);
        leftSectionElements.shouldHave(texts(SERVICE_OPTIONS.support,
                SERVICE_OPTIONS.dates, SERVICE_OPTIONS.complexTable, SERVICE_OPTIONS.simpleTable,
                SERVICE_OPTIONS.userTable, SERVICE_OPTIONS.tableWithWages,
                SERVICE_OPTIONS.differentElements, SERVICE_OPTIONS.performance));

    }

    @Step("Open page from navigation section")
    public void openPage(String page) {
        leftSectionElements.findBy(text(page)).click();
        $(byTitle(page.toLowerCase())).isDisplayed();

    }
}

