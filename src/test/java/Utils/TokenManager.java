package Utils;


import Clients.AuthClient;

public class TokenManager {


    private static String adminToken;

    public static String getAdminToken() {

        if (adminToken == null) {

            AdminCredentials creds = DataBaseUtils.getAdminCredentials();

            adminToken = AuthClient.getToken(
                    creds.getEmail(),
                    creds.getPassword()
            );
        }

        return adminToken;
    }
}