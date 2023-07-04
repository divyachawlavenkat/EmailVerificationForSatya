package mx.email.service;


import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

import javax.json.JsonObject;

public class EmailVerificationService {
    private static final String MX_TOOLBOX_API_URL = "https://api.mxtoolbox.com/api/v1/Lookup/mx/";
    private static final String MX_TOOLBOX_API_KEY = "YOUR_API_KEY";

    public boolean verifyEmail(String email) {
        String apiUrl = MX_TOOLBOX_API_URL + email;
        Client client = ClientBuilder.newClient();

        JsonObject response = client.target(apiUrl)
                .queryParam("Authorization", "Bearer " + MX_TOOLBOX_API_KEY)
                .request(MediaType.APPLICATION_JSON)
                .get(JsonObject.class);

        boolean isValid = response.getBoolean("Valid");

        client.close();
        return isValid;
    }
}
