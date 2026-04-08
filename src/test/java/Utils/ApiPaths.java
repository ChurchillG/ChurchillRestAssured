package Utils;

public class ApiPaths {
    public static final String BASE_PATH = "/APIDEV";

    public static final String LOGIN = "/login";
    public static final String USERS = "/register";
    public static final String APPROVE_USER = "/admin/users/{userId}/approve";
    public static final String USER_ROLE = "/admin/users/{userId}/role";
    public static final String CURRENT_USER = "/profile";
    public static final String DELETE_USER = "/admin/users/{userId}";
}
