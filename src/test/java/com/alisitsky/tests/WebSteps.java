package com.alisitsky.tests;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WebSteps {

    @Step("Открыть главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Найти репозиторий {repoName}")
    public void findRepository(String repoName) {
        $("button.input-button").click();
        $("input#query-builder-test").setValue(repoName).pressEnter();
    }

    @Step("Кликнуть по ссылке репозитория {linkText}")
    public void clickRepositoryLink(String linkText) {
        $$("span.search-match").findBy(text(linkText)).click();
    }

    @Step("Кликнуть в таб Issues")
    public void clickIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверить наличие Issue: {issueName}")
    public void checkIssueByName(String issueName) {
        $("#issue_1_link").shouldHave(text(issueName));
    }
}