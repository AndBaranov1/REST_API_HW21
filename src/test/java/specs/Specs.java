package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static tests.TestData.allureTestopsSession;
import static tests.TestData.xsrfToken;

public class Specs {

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .header("X-XSRF-TOKEN", xsrfToken)
            .cookie("XSRF-TOKEN", xsrfToken,
                    "ALLURE_TESTOPS_SESSION", allureTestopsSession)
            .contentType("application/json;charset=UTF-8")
            .baseUri("https://allure.autotests.cloud")
            .basePath("/api/rs");

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(ALL)
            //.expectContentType("application/json")
            .expectStatusCode(200)
            .build();


}
