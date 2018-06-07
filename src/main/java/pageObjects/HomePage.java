package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.util.Arrays;
import java.util.List;

public class HomePage {

    @FindBy(css = ".profile-photo")
    private WebElement userIcon;

    @FindBy(css = "#Name")
    private WebElement loginInput;

    @FindBy(css = "#Password")
    private WebElement passwordInput;

    @FindBy(css = ".form-horizontal button[type = 'submit']")
    private WebElement submitButton;

    @FindBy(css = ".profile-photo span")
    private WebElement userName;

    @FindBy(css = ".nav > li > a")
    private List<WebElement> actualSections;

    @FindBy(css = ".benefit-icon")
    private List<WebElement> actualImages;

    @FindBy(css = ".benefit-txt")
    private List<WebElement> actualTexts;

    @FindBy(css = "h3[name=main-title]")
    private WebElement mainTitle;

    @FindBy(css = "p[name=jdi-text]")
    private WebElement jdiText;

    @FindBy(xpath = "//a[text()=\"JDI Github\"]")
    private WebElement subHeaderElement;

    @FindBy(css = "div[name=navigation-sidebar]")
    private WebElement navigationSidebar;

    @FindBy(css = ".footer-bg")
    private WebElement footer;

    public void open(WebDriver driver) {
        driver.navigate().to("https://epam.github.io/JDI/");
    }

    public void checkHomePageTitle(WebDriver driver) {
        Assert.assertEquals(driver.getTitle(), "Home Page");
    }

    public void login(String login, String password) {
        userIcon.click();
        loginInput.sendKeys("epam");
        passwordInput.sendKeys("1234");
        submitButton.click();
    }

    public void checkUserName() {
        Assert.assertEquals(userName.getText(), "PITER CHAILOVSKII");
    }

    public void checkPageContainsElements() {
        List<String> expectedSections = Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
        int expectedSizeHeaderSections = 4;
        for (int i = 0; i < actualSections.size(); i++) {
            Assert.assertEquals(actualSections.get(i).getText(), expectedSections.get(i));
            Assert.assertEquals(actualSections.size(), expectedSizeHeaderSections);
        }
    }

    public void checkPageContainsImages() {
        int expectedSizeImages = 4;
        Assert.assertEquals(actualImages.size(), expectedSizeImages);
    }

    public void checkPageContainsTexts() {
        List<String> expectedTexts = Arrays.asList(
                "To include good practices\nand ideas from successful\nEPAM project",
                "To be flexible and\ncustomizable",
                "To be multiplatform",
                "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"
        );
        int expectedSizeTexts = 4;
        for (int i = 0; i < actualTexts.size(); i++) {
            Assert.assertEquals(actualTexts.get(i).getText(), expectedTexts.get(i));
            Assert.assertEquals(actualTexts.size(), expectedSizeTexts);
        }
    }

    public void checkPageContainsSpecialTexts() {
        String EpamText = "EPAM FRAMEWORK WISHES…";
        String LoremText = "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT " +
                "UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI " +
                "UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE" +
                " EU FUGIAT NULLA PARIATUR.";

        Assert.assertTrue(mainTitle.isDisplayed());
        Assert.assertEquals(mainTitle.getText(), EpamText);
        Assert.assertTrue(jdiText.isDisplayed());
        Assert.assertEquals(jdiText.getText(), LoremText);
    }

    public void checkTTextOfTheSubHeader() {
        String expectedSubHeaderText = "JDI GITHUB";
        Assert.assertTrue(subHeaderElement.isDisplayed());
        Assert.assertEquals(subHeaderElement.getText(), expectedSubHeaderText);
    }

    public void checkJDILinkURL() {
        String expectedURL = "https://github.com/epam/JDI";
        Assert.assertEquals(subHeaderElement.getAttribute("href"), expectedURL);
    }

    public void checkPageContainsNavigationSection() {
        Assert.assertTrue(navigationSidebar.isDisplayed());
    }

    public void checkPageContainsFooter() {
        Assert.assertTrue(footer.isDisplayed());
    }
}
