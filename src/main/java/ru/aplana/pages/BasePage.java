package ru.aplana.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aplana.utils.Init;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public BasePage() {
        PageFactory.initElements(Init.getDriver(), this);
    }

    public void clickOnElement(WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(Init.getDriver(), 30, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void inputInField(WebElement element, String inputText) {
        element.clear();
        element.sendKeys(inputText);
    }

    public void backSpaceSymbols(WebElement element, int count) {
        element.click();
        for (int i = 0; i < count; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void selectMenuItem(List<WebElement> menuElements, String name){
        for (WebElement element : menuElements ){
            if (element.getText().equalsIgnoreCase(name)){
                element.click();
                return;
            }
            System.out.println(element.getText());
        }
        Assert.fail("Не найден элмент коллеции - " + name);
    }

    /**
     * Проскролить до элемента
     */
    public void scrollDown(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Init.getDriver();
        Assert.assertNotNull(element);
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public boolean isElementPresent(WebElement element) {
        try {
            Init.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        finally {
            Init.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    public void waitCheckedValue(WebElement element, String expected) {
        WebDriverWait wait = new WebDriverWait(Init.getDriver(), 10);
        wait.withMessage(String.format("Неправильное значение [%s], ожидалось [%s]", element.getText(), expected))
                .until((ExpectedCondition<Boolean>) driver -> {
                    if (element.getText().equals(expected)) {
                        return Boolean.TRUE;
                    }
                    return false;
                });
    }
}