package pageObjects.selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.actions;

public class DataPageSelenide {

    @FindBy(css = "div[name=navigation-sidebar]")
    private SelenideElement navigationSidebar;

    @FindBy(css = "p > span")
    private ElementsCollection leftSectionElements;

    @FindBy(css = "div[name=log-sidebar]")
    private SelenideElement rightSection;

    @FindBy(css = "ul.panel-body-list.logs > li")
    private List<SelenideElement> logs;

    @FindBy(css = "a.ui-slider-handle")
    private ElementsCollection handles;

    @FindBy(css = ".ui-slider")
    private SelenideElement slider;

    @Step("Move left point")
    public void moveLeftPoint(int ValueLeft, int expectedPositionLeft) {
        SelenideElement leftPoint = handles.get(0);
        int size = slider.getSize().width;
        int actualPosition = Integer.parseInt(leftPoint.getText()) + 1;
        if (actualPosition < expectedPositionLeft) {
            actions().dragAndDropBy(leftPoint, ((expectedPositionLeft - actualPosition) * size) / 100, 0).perform();
        } else {
            actions().dragAndDropBy(leftPoint, (((actualPosition - expectedPositionLeft) * size) * -1) / 100, 0).perform();
        }
    }

    @Step("Move right point")
    public void moveRightPoint(int ValueRight, int expectedPositionRight) {
        SelenideElement rightPoint = handles.get(1);
        int size = slider.getSize().width;
        int actualPosition = Integer.parseInt(rightPoint.getText()) + 1;
        if (actualPosition < expectedPositionRight) {
            actions().dragAndDropBy(rightPoint, ((expectedPositionRight - actualPosition) * size) / 100, 0).perform();
        } else {
            actions().dragAndDropBy(rightPoint, (((actualPosition - expectedPositionRight) * size) * -1) / 100, 0).perform();
        }
    }

    @Step("Check logs for Range 2")
    public void checkLogsForRange(int logNumber, String type, int expectedPosition) {
        logs.get(logNumber - 1).shouldHave(text("Range 2(" + type + "):" + expectedPosition + " link clicked"));
    }
}





