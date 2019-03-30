package ru.aplana.steps;

import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        calcPage.clickOnElement(calcPage.programCheckBox);
        calcPage.scrollDown(calcPage.programCheckBox);
    }

    @When("выбрать вид ипотечной программы \"(.+)\"")
    public void fillProgram(String value) {
        Init.getDriver().findElement(By.xpath("//select[@id=\"form_program\"]/parent::div")).click();
        calcPage.selectMenuItem(calcPage.programMortrageList, value);
    }

    @When("выбрать статус клиента \"(.+)\"")
    public void fillStatus(String value) {
        Init.getDriver().findElement(By.xpath("//div[contains(text(),'Я являюсь')]//parent::div[@class=\"input-block input-block--triangle input-block--filled\"]")).click();
        calcPage.selectMenuItem(calcPage.clientStatusList, value);
    }

    @When("выбрать уровень доходов \"(.+)\"")
    public void fillIncomeLevel(String value) {
        Init.getDriver().findElement(By.xpath("//div[contains(text(),'Уровень доходов')]//parent::div[@class=\"input-block input-block--triangle input-block--filled\"]")).click();
        calcPage.selectMenuItem(calcPage.incomeLevelList, value);
    }

    @When("выбрать сумму в банке \"(.+)\"")
    public void fillTakeSum(String value) {
        calcPage.clickOnElement(calcPage.takeSumField);
        calcPage.inputInField(calcPage.takeSumField, value);
    }

    @When("выбрать первоначальный взнос \"(.+)\"")
    public void fillContribution(String value) {
        calcPage.backSpaceSymbols(calcPage.firstContributionField, 7);
        calcPage.firstContributionField.sendKeys(value);
    }

    @When("выбрать срок кредита \"(.+)\"")
    public void fillYearCount(String value) {
        calcPage.backSpaceSymbols(calcPage.yearCountField, 1);
        calcPage.inputInField(calcPage.yearCountField, value);
    }

    @When("нажать кнопку рассчитать")
    public void calculate() {
        calcPage.clickOnElement(calcPage.calculateBtn);
    }

    @When("проверить введенные значения String \"(.+)\" String \"(.+)\" String \"(.+)\" String \"(.+)\"")
    public void checkEnteredValues(String exMonthlyPayment, String exTotalPayment, String exPercentSum, String exInterestRate) {
        WebDriverWait wait = new WebDriverWait(Init.getDriver(), 30);
        wait.withMessage("Неправильные значения")
                .until((ExpectedCondition<Boolean>) driver -> {
                    if (calcPage.monthlyPayment.getText().replace("руб.", "").replaceAll(" ", "").equals(exMonthlyPayment)
                            && calcPage.totalPayment.getText().replace("руб.", "").replaceAll(" ", "").equals(exTotalPayment)
                            && calcPage.percentSum.getText().replace("руб.", "").replaceAll(" ", "").equals(exPercentSum)
                            && calcPage.interestRate.getText().replace("%", "").replaceAll(" ", "").equals(exInterestRate)) {
                        return Boolean.TRUE;
                    }
                    return false;
                });
    }
}