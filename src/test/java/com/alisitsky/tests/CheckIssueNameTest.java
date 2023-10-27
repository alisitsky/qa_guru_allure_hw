package com.alisitsky.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class CheckIssueNameTest {

    String repoName = "qa_guru_allure_hw";
    String linkText = "alisitsky/qa_guru_allure_hw";
    String issueName = "Test issue 1";

    @Test
    public void checkIssueNameTestWithListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $("button.input-button").click();
        $("input#query-builder-test").setValue(repoName).pressEnter();
        $$("span.search-match").findBy(text(linkText)).click();
        $("#issues-tab").click();
        $("#issue_1_link").shouldHave(text(issueName));
    }

    @Test
    public void checkIssueNameTestWithLambdaSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открыть главную страницу", () ->
                open("https://github.com") );

        step("Найти репозиторий " + repoName, () -> {
                $("button.input-button").click();
                $("input#query-builder-test").setValue(repoName).pressEnter();
        });

        step("Кликнуть по ссылке репозитория " + repoName, () ->
                $$("span.search-match").findBy(text(linkText)).click());

        step("Кликнуть в таб Issues", () ->
                $("#issues-tab").click());

        step("Проверить наличие Issue: " + issueName, () ->
                $("#issue_1_link").shouldHave(text(issueName)));
    }

    @Test
    public void checkIssueNameTestWithSteps() {
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.findRepository(repoName);
        steps.clickRepositoryLink(linkText);
        steps.clickIssuesTab();
        steps.checkIssueByName(issueName);
    }
}