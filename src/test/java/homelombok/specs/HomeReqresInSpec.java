package homelombok.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListner.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class HomeReqresInSpec {
    public static RequestSpecification homeReqresRequestSpec = with()
            .baseUri("https://reqres.in") //указан в BeforeAll
            .basePath("/api")
            .filter(withCustomTemplates())
            .log().uri()
            .log().body();

    public static ResponseSpecification homeReqresResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();

}
