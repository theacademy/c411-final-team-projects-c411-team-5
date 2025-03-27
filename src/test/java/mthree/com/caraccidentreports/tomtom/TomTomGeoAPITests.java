package mthree.com.caraccidentreports.tomtom;


import mthree.com.caraccidentreports.App;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@SpringBootTest(classes = App.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TomTomGeoAPITests {

    @Value("${spring.tomtom.key}")
    private String apiKey;

    @Autowired
    private RestClient restClient;

    private String BASE_URL = "https://api.tomtom.com/search/2/geocode/%s.json?key=%s";

    @Test
    @DisplayName("Is Tom Tom Geocode online")
    public void testIsTomTomGeoCodeOnline() {
        try {
            // URL to send the request to
            URL url = new URL(String.format(BASE_URL, "Los%20Angeles", apiKey));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the response

            // Parse the response as JSON
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Print the response in JSON format
            System.out.println("Response: " + jsonResponse.toString(4)); // Pretty print with 4 spaces


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
