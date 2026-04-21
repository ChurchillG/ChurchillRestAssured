package Utils;
import io.qameta.allure.Step;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBaseUtils {

    // Read from environment variables first (for CI/CD like GitHub Actions)
    private static final String URL = System.getenv("DB_URL") != null
            ? System.getenv("DB_URL")
            : System.getProperty("db.url",
            "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_teaching");

    private static final String USER = System.getenv("DB_USERNAME") != null
            ? System.getenv("DB_USERNAME")
            : System.getProperty("db.user",
            "ndosian6b8b7_teaching");

    private static final String PASSWORD = System.getenv("DB_PASSWORD") != null
            ? System.getenv("DB_PASSWORD")
            : System.getProperty("db.password",
            "^{SF0a=#~[~p)@l1");

    @Step("Fetch admin credentials from DB")
    public static AdminCredentials getAdminCredentials() {

        String query = "SELECT * FROM loginUser WHERE id = 7";

        try (
                Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement stmt = con.prepareStatement(query)
        ) {

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new AdminCredentials(
                            rs.getString("email"),
                            rs.getString("password")
                    );
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch admin credentials from DB", e);
        }

        throw new RuntimeException("No ADMIN user found in DB");
    }
}
