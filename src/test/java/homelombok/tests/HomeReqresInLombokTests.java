package homelombok.tests;

import homelombok.models.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static homelombok.specs.HomeReqresInSpec.homeReqresRequestSpec;
import static homelombok.specs.HomeReqresInSpec.homeReqresResponseSpec;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class HomeReqresInLombokTests {

    @Test
    void checkIdTheSecondUser() {
        given()
                .spec(homeReqresRequestSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(homeReqresResponseSpec)
                .statusCode(200)
                .body("data.id", is(2));
    }

    @Test
    void check404WrongUser() {
        given()
                .spec(homeReqresRequestSpec)
                .when()
                .get("/users/23")
                .then()
                .spec(homeReqresResponseSpec)
                .statusCode(404);
    }

    @Test
    void checkSuccessfulCreate(){
//        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
        CreateRequestBody requestBody = new CreateRequestBody();
        requestBody.setName("morpheus");
        requestBody.setJob("leader");

        CreateResponseBody responseBody = given()
                .spec(homeReqresRequestSpec)
                .when()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/users")
                .then()
                .spec(homeReqresResponseSpec)
                .statusCode(201)
                .extract().as(CreateResponseBody.class);
//                .body("name", is("morpheus"))
//                .body("job", is("leader"));

        assertThat(responseBody.getName()).isEqualTo("morpheus");
        assertThat(responseBody.getJob()).isEqualTo("leader");
    }

    @Test
    void checkSuccessfulRegister(){
//        String requestBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";
        RegisterRequestBody requestBody = new RegisterRequestBody();
        requestBody.setEmail("eve.holt@reqres.in");
        requestBody.setPassword("pistol");

        RegisterResponseBody responseBody = given()
                .spec(homeReqresRequestSpec)
                .when()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/register")
                .then()
                .spec(homeReqresResponseSpec)
                .statusCode(200)
                .extract().as(RegisterResponseBody.class);
//                .body("token", is("QpwL5tke4Pnpja7X4"));

        assertThat(responseBody.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void checkSuccessfulUserUpdate(){
//        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";
        UserDataUpdateRequestBody requestBody = new UserDataUpdateRequestBody();
        requestBody.setName("morpheus");
        requestBody.setJob("zion resident");

        UserDataUpdateResponseBody responseBody = given()
                .spec(homeReqresRequestSpec)
                .when()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put("/users/2")
                .then()
                .spec(homeReqresResponseSpec)
                .statusCode(200)
                .extract().as(UserDataUpdateResponseBody.class);
//                .body("name", is("morpheus"));

        assertThat(responseBody.getName()).isEqualTo("morpheus");
        assertThat(responseBody.getJob()).isEqualTo("zion resident");
        assertThat(responseBody.getUpdatedAt()).isNotNull();
    }

    @Test
    void getSingleUser(){
        UserResponseBody responseBody = given()
                .spec(homeReqresRequestSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(homeReqresResponseSpec)
                .statusCode(200)
                .extract().as(UserResponseBody.class);

        assertThat(responseBody.getUser().getId()).isEqualTo(2);
        assertThat(responseBody.getUser().getFirstName()).isEqualTo("Janet");
    }

    @Test
    void checkUsersEmail(){
        given()
                .spec(homeReqresRequestSpec)
                .when()
                .get("/users?page=2")
                .then()
                .spec(homeReqresResponseSpec)
                .statusCode(200)
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",hasItem("lindsay.ferguson@reqres.in"));
    }

}
