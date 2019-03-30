package ru.aplana.steps;

import cucumber.api.java.en.When;
import ru.aplana.pages.MainPage;

public class MainSteps {


    @When("загружена страница Ипотечный калькулятор")
    public void loadPage() {
        MainPage mainPage = new MainPage();
        mainPage.selectMenuItem(mainPage.mainMenu, "Ипотека");
        mainPage.clickOnElement(mainPage.calculator);
    }
}