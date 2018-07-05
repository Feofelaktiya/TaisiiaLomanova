package pageObjects.cucumber;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.*;
import static enums.Texts.rangeLogs.RANGE_LOGS;
import static enums.Texts.serviceOptions.SERVICE_OPTIONS;
import static enums.Users.PITER_CHAILOVSKII;

public class SelenideCucumber {

    public SelenideCucumber() {
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

    @FindBy(css = "a.ui-slider-handle")
    private ElementsCollection handles;

    @FindBy(css = ".ui-slider")
    private SelenideElement slider;

    @Step("Open site")
    @Given("I am on the Home Page")
    public void openHomePage() {
        open("https://epam.github.io/JDI/");
    }

    @Step("Check home title")
    @Then("The browser title is Home Page")
    public void checkBrowserTitleIsHomePage() {
        $(byTitle("Home Page")).isDisplayed();
    }

    @Step("Login")
    @When("I login as user (.+) with password (.+)")
    public void login(String login, String password) {
        userIcon.click();
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        submitButton.click();
    }

    @Step ("Check user name")
    @Then("The user name is displayed")
    public void checkUserName() {
        userName.isDisplayed();
        userName.shouldHave(text(PITER_CHAILOVSKII.name));
    }

    @Step("Open Data Page")
    @When("I open the Data Page")
    public void openPage() {
        leftSectionElements.findBy(text(SERVICE_OPTIONS.dates)).click();
        $(byTitle(SERVICE_OPTIONS.dates.toLowerCase())).isDisplayed();
    }

    @Step ("Check browser title is Data Page")
    @Then("The browser title is Data Page")
    public void checkBrowserTitleIsDataPage() {
        $(byTitle("Data Page".toLowerCase())).isDisplayed();
    }

    @Step ("Move points: left to left side, right to right side")
    @When("I move left point to (\\d+) position")
    public void moveLeftPoint(int expectedPositionLeft) {
        SelenideElement leftPoint = handles.get(0);
        int size = slider.getSize().width;
        int actualPosition = Integer.parseInt(leftPoint.getText()) + 1;
        if (actualPosition < expectedPositionLeft) {
            actions().dragAndDropBy(leftPoint, ((expectedPositionLeft - actualPosition) * size) / 100, 0).perform();
        } else {
            actions().dragAndDropBy(leftPoint, (((actualPosition - expectedPositionLeft) * size) * -1) / 100, 0).perform();
        }
    }

    @And("I move right point to (\\d+) position")
    public void moveRightPoint(int expectedPositionRight) {
        SelenideElement rightPoint = handles.get(1);
        int size = slider.getSize().width;
        int actualPosition = Integer.parseInt(rightPoint.getText()) + 1;
        if (actualPosition < expectedPositionRight) {
            actions().dragAndDropBy(rightPoint, ((expectedPositionRight - actualPosition) * size) / 100, 0).perform();
        } else {
            actions().dragAndDropBy(rightPoint, (((actualPosition - expectedPositionRight) * size) * -1) / 100, 0).perform();
        }
    }

    @Step ("Check logs have appropriate message rows")
    @Then("I check log for left point: (\\d+) log with (\\d+) value")
    public void checkLogsForLeftPoint(int logNumber, int expectedPosition) {
        logs.get(logNumber - 1).shouldHave(text("Range 2(" + RANGE_LOGS.from + "):" + expectedPosition + " link clicked"));
    }

    @And ("I check log for right point: (\\d+) log with (\\d+) value")
    public void checkLogsForRightPoint(int logNumber, int expectedPosition) {
        logs.get(logNumber - 1).shouldHave(text("Range 2(" + RANGE_LOGS.to + "):" + expectedPosition + " link clicked"));
    }
}