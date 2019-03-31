package ru.aplana.steps;

import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import ru.aplana.pages.CalcPage;
import ru.aplana.utils.Init;

public class CalcSteps {

    private CalcPage calcPage = new CalcPage();

    @When("проверить заголовок страницы")
    public void checkTitle() {
        if (calcPage.mortrageText.getText().equals("Ипотечный калькулятор")) {
            calcPage.scrollDown(calcPage.mortrageText);
            return;
        }
        Assert.fail("Неправильный заголовок страницы");
    }

    @When("выбрать город \"(.+)\"")
    public void fillCity(String value) {
        Init.getDriver().findElement(By.xpath("//select[@id=\"form_city\"]/parent::div")).click();
        calcPage.selectMenuItem(calcPage.cityList, value);
    }

    @When("выбрать ипотечную программу")
    public void clickCheckBox() {
        calcPage.waitPageLoaded();
        calcPage.clickOnElement(calcPage.programCheckBox);
        calcPage.scrollDown(calcPage.programCheckBox);
    }

    @When("выбрать вид ипотечной программы \"(.+)\"")
    public void fillProgram(String value) {
        calcPage.waitPageLoaded();
        Init.getDriver().findElement(By.xpath("//select[@id=\"form_program\"]/parent::div")).click();
        calcPage.selectMenuItem(calcPage.programMortrageList, value);
    }

    @When("выбрать статус клиента \"(.+)\"")
    public void fillStatus(String value) {
        calcPage.waitPageLoaded();
        Init.getDriver().findElement(By.xpath("//div[contains(text(),'Я являюсь')]//parent::div[@class=\"input-block input-block--triangle input-block--filled\"]")).click();
        calcPage.selectMenuItem(calcPage.clientStatusList, value);
    }

    @When("выбрать уровень доходов \"(.+)\"")
    public void fillIncomeLevel(String value) {
        calcPage.waitPageLoaded();
        Init.getDriver().findElement(By.xpath("//div[contains(text(),'Уровень доходов')]//parent::div[@class=\"input-block input-block--triangle input-block--filled\"]")).click();
        calcPage.selectMenuItem(calcPage.incomeLevelList, value);
    }

    @When("выбрать сумму в банке \"(.+)\"")
    public void fillTakeSum(String value) {
        calcPage.waitPageLoaded();
        calcPage.clickOnElement(calcPage.takeSumField);
        calcPage.inputInField(calcPage.takeSumField, value);
    }

    @When("выбрать первоначальный взнос \"(.+)\"")
    public void fillContribution(String value) {
        calcPage.waitPageLoaded();
        calcPage.backSpaceSymbols(calcPage.firstContributionField, value);
        calcPage.firstContributionField.sendKeys(value);
    }

    @When("выбрать срок кредита \"(.+)\"")
    public void fillYearCount(String value) {
        calcPage.waitPageLoaded();
        calcPage.backSpaceSymbols(calcPage.yearCountField, value);
        calcPage.inputInField(calcPage.yearCountField, value);
    }

    @When("нажать кнопку рассчитать")
    public void calculate() {
        calcPage.waitPageLoaded();
        calcPage.clickOnElement(calcPage.calculateBtn);
    }

    @When("проверить введенные значения String \"(.+)\" String \"(.+)\" String \"(.+)\" String \"(.+)\"")
    public void checkEnteredValues(String exMonthlyPayment, String exTotalPayment, String exPercentSum, String exInterestRate) {
        calcPage.checkElement(calcPage.monthlyPayment.getText().replace("руб.", "").replaceAll(" ", ""), exMonthlyPayment);
        calcPage.checkElement(calcPage.totalPayment.getText().replace("руб.", "").replaceAll(" ", ""), exTotalPayment);
        calcPage.checkElement(calcPage.percentSum.getText().replace("руб.", "").replaceAll(" ", ""), exPercentSum);
        calcPage.checkElement(calcPage.interestRate.getText().replace("%", "").replaceAll(" ", ""), exInterestRate);
    }
}