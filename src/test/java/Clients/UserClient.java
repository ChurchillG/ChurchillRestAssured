package Clients;
import Utils.ApiPaths;
import Utils.PayloadBuilder;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class UserClient {
    @Step("Create new user: {email}")
    public Response createUser(String firstName, String lastName, String email, String password, String confirmPassword) {

        return given()
                .contentType("application/json")
                .body(PayloadBuilder.createUserPayload(firstName,lastName, email, password,confirmPassword))
                .log().all()
                .when()
                .post(ApiPaths.USERS)
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Approve user: {userId}")
    public Response approveUser(String userId, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .pathParam("userId", userId)
                .when()
                .put(ApiPaths.APPROVE_USER)
                .then()
                .extract().response();
    }

    @Step("Make user ADMIN: {userId}")
    public Response makeAdmin(String userId, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(PayloadBuilder.rolePayload("admin"))
                .pathParam("userId", userId)
                .when()
                .put(ApiPaths.USER_ROLE)
                .then()
                .extract().response();
    }

    @Step("Get current user")
    public Response getCurrentUser(String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(ApiPaths.CURRENT_USER)
                .then()
                .extract().response();
    }

    @Step("Delete user: {userId}")
    public Response deleteUser(String userId, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .pathParam("userId", userId)
                .when()
                .delete(ApiPaths.DELETE_USER)
                .then()
                .extract().response();
    }
}
