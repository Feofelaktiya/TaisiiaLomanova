package lesson4;

import base.TestBase;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static enums.Users.PITER_CHAILOVSKII;

/**
 * Created by Danila_Morokov on 5/18/2018.
 */
//@Listeners({AllureAttachmentListener.class})
public class SimpleTest extends TestBase {

    @Test
    public void simpleSeleniumTest() {

        open("https://epam.github.io/JDI/index.html");
        Assert.assertEquals(getWebDriver().getTitle(), "Home Page");

        $(".profile-photo").click();

        $("#Name").sendKeys(PITER_CHAILOVSKII.login);
        $("#Password").sendKeys(PITER_CHAILOVSKII.password);
        $(".form-horizontal button[type = 'submit']").click();

        SelenideElement userName = $(".profile-photo span");
        userName.shouldBe(visible);
        userName.shouldHave(text(PITER_CHAILOVSKII.name));

        $$(".benefit-icon").shouldHaveSize(4);
        $$(".benefit-icon").shouldBe(CollectionCondition.texts("", "", "", ""));
    }
}