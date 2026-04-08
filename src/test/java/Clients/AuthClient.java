package Clients;
import Utils.ApiPaths;
import Utils.PayloadBuilder;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.given;
public class AuthClient {
    @Step("Login with email: {email}")
    public static Response loginUserResponse(String email, String password) {

        Response response =
                given()
                        .contentType("application/json")
                        .body(PayloadBuilder.loginPayload(email, password))
                        .log().all()
                        .when()
                        .post(ApiPaths.LOGIN)
                        .then()
                        .log().all()
                        .extract().response();

        return response;
    }

    public static String getToken(String email, String password) {
        return loginUserResponse(email, password)
                .jsonPath().getString("data.token");
    }

}
