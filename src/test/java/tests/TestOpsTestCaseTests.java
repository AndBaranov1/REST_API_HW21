package tests;

import com.github.javafaker.Faker;
import models.AddDescriptionBodyModel;
import models.AddDescriptionResponseModel;
import models.CreateTestCaseBodyModel;
import models.CreateTestCaseResponseModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.TestCasesPage;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.Specs.requestSpec;
import static specs.Specs.responseSpec;
import static tests.TestData.login;
import static tests.TestData.password;
import static tests.TestData.projectId;

public class TestOpsTestCaseTests extends TestBase{

    TestCasesPage testCasesPage = new TestCasesPage();
    LoginPage loginPage = new LoginPage();
    Faker faker = new Faker();

    String testCaseNameInitial = faker.artist().name();
    String testCaseNameUpdated = faker.book().author();
    String testCaseDescriptionInitial = faker.address().fullAddress();
    String testCaseDescriptionUpdated = faker.color().name();
    String stepNameInitial = faker.book().title();
    String stepNameUpdated = faker.book().genre();

    @Tag("update")
    @Test
    void updateTestCaseNameAndDescriptionTest() {
        CreateTestCaseBodyModel createTestCaseBody = new CreateTestCaseBodyModel();
        createTestCaseBody.setName(testCaseNameInitial);

        step("Authorize", () -> {
            loginPage.openPage()
                    .setLogin(login)
                    .setPassword(password)
                    .clickSubmit();
            testCasesPage.checkSideMenu();
        });

        step("Go to project", () ->
                testCasesPage.openProjectPage());

        CreateTestCaseResponseModel createTestCaseResponse = step("Create testcase", () ->
                given(requestSpec)
                        .body(createTestCaseBody)
                        .queryParam("projectId", projectId)
                        .when()
                        .post("/testcasetree/leaf")
                        .then()
                        .spec(responseSpec)
                        .extract().as(CreateTestCaseResponseModel.class));

        Integer testCaseId = createTestCaseResponse.getId();

        AddDescriptionBodyModel addDescriptionBody = new AddDescriptionBodyModel();
        addDescriptionBody.setId(testCaseId);
        addDescriptionBody.setDescription(testCaseDescriptionInitial);
        addDescriptionBody.setDescriptionHtml(null);

        AddDescriptionResponseModel addDescriptionResponse = step("Add description to testcase", () ->
                given(requestSpec)
                        .body(addDescriptionBody)
                        .when()
                        .patch("/testcase/" + testCaseId)
                        .then()
                        .spec(responseSpec)
                        .extract().as(AddDescriptionResponseModel.class));

        step("Open test case editor", () ->
                testCasesPage.openTestCaseEditor(projectId, testCaseId));

        step("Check test case name", () ->
                testCasesPage.checkTestCaseNameInEditor(testCaseNameInitial));

        step("Check description", () ->
                testCasesPage.checkTestCaseDescription(testCaseDescriptionInitial));

        step("Update test case name", () ->
                testCasesPage.updateTestCaseName(testCaseNameUpdated));

        step("Check updated test case name", () ->
                testCasesPage.checkTestCaseNameInEditor(testCaseNameUpdated));

        step("Update description", () ->
                testCasesPage.updateTestCaseDescription(testCaseDescriptionUpdated));

        step("Check updated description", () ->
                testCasesPage.checkTestCaseDescription(testCaseDescriptionUpdated));
    }

    @Test
    @Tag("update")
    void addStepToTestCaseTest() {
        CreateTestCaseBodyModel createTestCaseBody = new CreateTestCaseBodyModel();
        createTestCaseBody.setName(testCaseNameInitial);

        step("Authorize", () -> {
            loginPage.openPage()
                    .setLogin(login)
                    .setPassword(password)
                    .clickSubmit();
            testCasesPage.checkSideMenu();
        });

        step("Go to project", () ->
                testCasesPage.openProjectPage());

        CreateTestCaseResponseModel createTestCaseResponse = step("Create testcase", () ->
                given(requestSpec)
                        .body(createTestCaseBody)
                        .queryParam("projectId", projectId)
                        .when()
                        .post("/testcasetree/leaf")
                        .then()
                        .spec(responseSpec)
                        .extract().as(CreateTestCaseResponseModel.class));

        Integer testCaseId = createTestCaseResponse.getId();

        step("Open test case editor", () ->
                testCasesPage.openTestCaseEditor(projectId, testCaseId));

        step("Check test case name", () ->
                testCasesPage.checkTestCaseNameInEditor(testCaseNameInitial));

        step("Add step to test case", () ->
                testCasesPage.addStepToTestCase(stepNameInitial));

        step("Check step name", () ->
                testCasesPage.checkStepName(stepNameInitial));
    }


}
