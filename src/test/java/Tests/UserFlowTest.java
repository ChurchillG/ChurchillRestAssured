package Tests;
import Base.BaseTest;
import Clients.AuthClient;
import Clients.UserClient;
import Utils.TokenManager;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("User Management")
@Feature("Full User Lifecycle")
public class UserFlowTest extends BaseTest {

    UserClient userClient = new UserClient();
    String firstName = "TestFirstNametest";
    String lastName = "TestLastNametest";

    String email = "test" + System.currentTimeMillis() + "@gmail.com";
    String password = "!12345678";
    String confirmPassword = "!12345678";

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create → Approve → Promote → Verify → Delete user")
    public void userFlowTest() {

        // Create user
        Response createRes = userClient.createUser(firstName, lastName, email, password,confirmPassword);
        createRes.then().statusCode(201);

        String userId = createRes.jsonPath().getString("data.id");

        // Admin token
        String adminToken = TokenManager.getAdminToken();

        // Approve
        //userClient.approveUser(userId, adminToken).then().statusCode(200);
        Response approveRes = userClient.approveUser(userId, adminToken);
        approveRes.then().log().all();
        approveRes.then().statusCode(200);

        // Promote
        userClient.makeAdmin(userId, adminToken).then().statusCode(200);


        // Login new user
        String newUserToken = AuthClient.getToken(email, password);

        // Verify
        Response meRes = userClient.getCurrentUser(newUserToken);
        meRes.then().log().all();
        Assert.assertEquals(meRes.jsonPath().getString("data.Role"), "admin");

        // Delete
        //userClient.deleteUser(userId, adminToken).then().statusCode(200);
        Response deleteRes = userClient.deleteUser(userId, adminToken);
        deleteRes.then().log().all();
        deleteRes.then().statusCode(200);
    }
}