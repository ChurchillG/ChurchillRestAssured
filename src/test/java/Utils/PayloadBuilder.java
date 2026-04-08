package Utils;
import java.util.HashMap;
import java.util.Map;
public class PayloadBuilder {
    public static Map<String, Object> loginPayload(String email, String password) {
        Map<String, Object> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        return body;
    }

    public static Map<String, Object> createUserPayload(String firstName, String lastName,String email, String password,String confirmPassword) {
        Map<String, Object> body = new HashMap<>();
        body.put("firstName", firstName);
        body.put("lastName", lastName);
        body.put("email", email);
        body.put("password", password);
        body.put("confirmPassword", confirmPassword);
        body.put("groupId", "1deae17a-c67a-4bb0-bdeb-df0fc9e2e526");
        return body;
    }

    public static Map<String, Object> rolePayload(String role) {
        Map<String, Object> body = new HashMap<>();
        body.put("role", role);
        return body;
    }
    public static Map<String, Object> approveUserPayload() {
        Map<String, Object> body = new HashMap<>();
        body.put("approvalStatus", "approved");
        return body;
    }
}
