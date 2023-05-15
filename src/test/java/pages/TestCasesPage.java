package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static tests.TestData.allureTestopsSession;
import static tests.TestData.projectId;

public class TestCasesPage {

    private final String PROJECT_URL = "/project/" + projectId + "/test-cases";
    private final SelenideElement
            sideMenu = $("[data-testid=sidemenu]"),
            testCaseNameSideBarInput = $("[data-testid=input__create_test_case]"),
            sideBar = $(".LoadableTree__view"),
            testCaseNameEditor = $(".TestCaseLayout__name"),
            testCaseDescription = $("[data-testid=section__description]"),
            menuTriggerAtHeader = $(".Menu__trigger"),
            testCaseNameInputAtHeader = $("[placeholder='Enter name']"),
            editDescriptionButton = $("[data-testid=section__description]").$("[data-testid=button__edit_section]"),
            descriptionInput = $("[data-testid=section__description]").$(".MarkdownTextarea__edit"),
            editStepButton = $("[data-testid=section__scenario]").$("[data-testid=button__edit_section]"),
            stepInput = $(".TestCaseScenarioStepEdit__textarea"),
            testCaseScenario = $(".TestCaseScenarioStep__name");

    public TestCasesPage checkSideMenu() {
        sideMenu.shouldBe(visible);

        return this;
    }

    public TestCasesPage openProjectPage() {
        open(PROJECT_URL);

        return this;
    }

    public TestCasesPage setTestcaseName(String value) {
        testCaseNameSideBarInput.setValue(value).pressEnter();

        return this;
    }

    public TestCasesPage checkTestCaseNameAtSideBar(String value) {
        sideBar.shouldHave(text(value));

        return this;
    }

    public void openTestCaseEditor(String projectId, Integer testCaseId) {
        open("/favicon.ico");
        Cookie authorizationCookie = new Cookie(
                "ALLURE_TESTOPS_SESSION", allureTestopsSession);
        getWebDriver().manage().addCookie(authorizationCookie);
        open("/project/" + projectId + "/test-cases/" + testCaseId);
    }

    public TestCasesPage checkTestCaseNameInEditor(String value) {
        testCaseNameEditor.shouldHave(text(value));

        return this;
    }

    public TestCasesPage checkTestCaseDescription(String value) {
        testCaseDescription.shouldHave(text(value));

        return this;
    }

    public TestCasesPage updateTestCaseName(String value) {
        menuTriggerAtHeader.click();
        $(byText("Rename test case")).click();
        testCaseNameInputAtHeader.clear();
        testCaseNameInputAtHeader.setValue(value);
        $(byText("Submit")).click();

        return this;
    }

    public TestCasesPage updateTestCaseDescription(String value) {
        editDescriptionButton.click();
        descriptionInput.clear();
        descriptionInput.setValue(value);
        $(byText("Submit")).click();

        return this;
    }

    public TestCasesPage addStepToTestCase(String value) {
        editStepButton.click();
        stepInput.setValue(value);
        $(byText("Submit")).click();

        return this;
    }

    public TestCasesPage checkStepName(String value) {
        testCaseScenario.shouldHave(text(value));

        return this;
    }

    public TestCasesPage updateStepName(String value) {
        editStepButton.click();
        stepInput.clear();
        stepInput.setValue(value);
        $(byText("Submit")).click();

        return this;
    }
}
