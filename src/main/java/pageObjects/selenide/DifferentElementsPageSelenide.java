package pageObjects.selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static enums.Texts.checkboxTexts.CHECKBOX_TEXTS;
import static enums.Texts.radiobuttonTexts.RADIOBUTTON_TEXTS;
import static enums.Texts.webElementTypes.WEB_ELEMENT_TYPES;

public class DifferentElementsPageSelenide {

    @FindBy(css = "div[name=navigation-sidebar]")
    private SelenideElement navigationSidebar;

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

    @FindBy(css = "ul.panel-body-list.logs > li")
    private List<SelenideElement> logs;

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
        navigationSidebar.isDisplayed();
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

    public void checkLogForCheckboxex(int logNumber, String type, boolean checkedStatus) {
        logs.get(logNumber - 1).shouldHave(text(type + ": condition changed to " + checkedStatus));
    }

    public void checkLogForRadiobuttons(int logNumber, String metal) {
        logs.get(logNumber - 1).shouldHave(text("metal: value changed to " + metal));
    }

    public void checkLogForDropdown(int logNumber, String color) {
        logs.get(logNumber - 1).shouldHave(text("Colors: value changed to " + color));
    }
}

