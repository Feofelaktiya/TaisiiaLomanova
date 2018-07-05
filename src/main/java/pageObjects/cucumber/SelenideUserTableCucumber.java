package pageObjects.cucumber;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.id;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static enums.Texts.serviceOptions.SERVICE_OPTIONS;
import static enums.Texts.usersInTable.USERS_IN_TABLE;
import static enums.Users.PITER_CHAILOVSKII;

public class SelenideUserTableCucumber {

    public SelenideUserTableCucumber() {
        page(this);
    }

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

    @FindBy(css = "p > span")
    private ElementsCollection leftSectionElements;

    @FindBy(css = "ul.panel-body-list.logs > li")
    private List<SelenideElement> logs;

    @FindBy(xpath = "//tbody/tr//td[1]")
    private List<WebElement> numbers;

    @FindBy(xpath = "//tbody/tr//td[3]/a")
    private List<WebElement> users;

    @FindBy(css = ".user-descr span")
    private List<WebElement> descriptions;

    @FindBy(css = "input[type=checkbox]")
    private ElementsCollection vipCheckboxes;

    @FindBy(xpath = "//a[contains(text(), 'Roman')]/ancestor::tr//select/option")
    private List<WebElement> options;

    List<String> numbersList = new LinkedList<>();
    List<String> usersList = new LinkedList<>();
    List<String> descList = new LinkedList<>();
    List<String> dropdownList = new LinkedList<>();

    @Step("Login and check user name")
    @And("I am logged in as Piter Chailovskii with login (.+) and (.+) password")
    public void loginAsPiter(String login, String password) {
        userIcon.click();
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        submitButton.click();
        userName.isDisplayed();
        userName.shouldHave(text(PITER_CHAILOVSKII.name));
    }

    @Step("Open User Table page")
    @And("I open User Table Page through the header menu Service -> User Table")
    public void openUserTablePage() {
        leftSectionElements.findBy(text(SERVICE_OPTIONS.userTable)).click();
    }

    @And("I am on Users Table Page")
    public void checkPageTitleIsUserTable() {
        $(byTitle(SERVICE_OPTIONS.userTable.toLowerCase())).isDisplayed();
    }

    @Step("Check number and user columns have valid values")
    @And("I check Number and User columns of Users table")
    public void getNamesAndNumbers() {
        for (WebElement number : numbers) {
            numbersList.add(number.getText());
        }
        for (WebElement user : users) {
            usersList.add(user.getText());
        }
    }

    @Then("User table contain correct values for numbers and users")
    public void checkNumberAndNames(DataTable usersWithNumbers) {
        List<String> expectedNumber = new LinkedList<>();
        List<String> expectedUser = new LinkedList<>();
        Map<String, String> userRow = usersWithNumbers.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : userRow.entrySet()) {
            expectedNumber.add(entry.getKey());
            expectedUser.add(entry.getValue());
        }
        Assert.assertEquals(numbersList, expectedNumber);
        Assert.assertEquals(usersList, expectedUser);
    }

    @Step("Check description column has valid values")
    @When("I check Description column of Users table")
    public void getDescription() {
        for (WebElement description : descriptions
                ) {
            descList.add(description.getText());
        }
    }

    @Then("All cells of 'Description' column contains text")
    public void checkDescriptionContainsTexts(DataTable descWithNumbers) {
        List<String> expectedNumber = new LinkedList<>();
        List<String> expectedDescription = new LinkedList<>();
        Map<String, String> userRow = descWithNumbers.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : userRow.entrySet()) {
            expectedNumber.add(entry.getKey());
            expectedDescription.add(entry.getValue());
        }
    }

    @Step("Set Vip status to a user")
    @When("I set 'vip' status to (.+)")
    public void setStatus(String name) {
        if (name.equals(USERS_IN_TABLE.ivan)) {
            vipCheckboxes.findBy(id("ivan")).click();
        }
    }

    @Step("Check logs for vip status")
    @Then("'Log' section shows a (\\d+) log row in format: (.+): condition changed to (.+)")
    public void checkLogForVipStatus(int logNumber, String log, String condition) {
        logs.get(logNumber - 1).shouldHave(text(log + ": condition changed to " + condition));
    }

    @Step("Check dropdown options have valid values")
    @When("I click on dropdown in column Type for user (.+)")
    public void getDropdownValues(String user) {
        if (user.equals(USERS_IN_TABLE.roman)) {
            $(By.xpath("//a[contains(text(), 'Roman')]/ancestor::tr//select")).click();
            for (WebElement option : options
                    ) {
                dropdownList.add(option.getText());
            }
        }
    }

    @Then("Droplist contains values")
    public void droplistContainsValues(DataTable options) {
        List<String> expectedOptions = options.asList();
        Assert.assertEquals(dropdownList, expectedOptions);
    }
}