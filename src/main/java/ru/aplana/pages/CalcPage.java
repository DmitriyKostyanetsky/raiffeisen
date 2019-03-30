package ru.aplana.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CalcPage extends BasePage {

    @FindBy(xpath = "//h1[contains(text(), 'Ипотечный калькулятор')]")
    public WebElement mortrageText;

    @FindBy(xpath = "//ul[@aria-labelledby=\"form_city-button\"]//li")
    public List<WebElement> cityList;

    @FindBy(xpath = "//label[@class=\"checkbox-block__label\"]")
    public WebElement programCheckBox;

    @FindBy(xpath = "//ul[@aria-labelledby=\"form_program-button\"]//child::li")
    public List<WebElement> programMortrageList;

    @FindBy(xpath = "//ul[@id=\"form_category-menu\"]//child::li")
    public List<WebElement> clientStatusList;

    @FindBy(xpath = "//ul[@id=\"form_documents-menu\"]//child::li")
    public List<WebElement>  incomeLevelList;

    @FindBy(xpath = "//input[@id=\"form_credit_amount\"]")
    public WebElement takeSumField;

    @FindBy(xpath = "//input[@id=\"form_initial\"]")
    public WebElement firstContributionField;

    @FindBy(xpath = "//input[@id=\"form_period\"]")
    public WebElement yearCountField;

    @FindBy(xpath = "//input[@value=\"Рассчитать\"]")
    public WebElement calculateBtn;

    @FindBy(xpath = "//span[@class=\"monthly-payment\"]")
    public WebElement monthlyPayment;

    @FindBy(xpath = "//div[@class=\"b-calc-result__text--right total-payment\"]")
    public WebElement totalPayment;

    @FindBy(xpath = "//div[@class=\"b-calc-result__text--right percent-sum\"]")
    public WebElement percentSum;

    @FindBy(xpath = "//div[@class=\"b-calc-result__text--right interest-rate\"]")
    public WebElement interestRate;
}