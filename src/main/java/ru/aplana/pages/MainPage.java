package ru.aplana.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(xpath = "//ul[@class='main-menu']//li")
    public List<WebElement> mainMenu;

    @FindBy(xpath = "//a[@href=\"/retail/mortgageloans/calculator/\"]")
    public WebElement calculator;

    public void selectMenuItem(String name){
        selectMenuItem(mainMenu, name);
    }
}