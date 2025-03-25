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
public class TomTomAPITests {

    @Value("${spring.tomtom.key}")
    private String apiKey;

    @Autowired
    private RestClient restClient;

    @Value("${spring.tomtom.baseurl}")
    private String BASE_URL;

    @Test
    @DisplayName("Is Tom Tom online")
    public void testIsTomTomOnline() {
        try {
            // URL to send the request to
            URL url = new URL("https://api.tomtom.com/traffic/services/5/incidentDetails?" +
                    "bbox=4.8854592519716675%2C52.36934334773164%2C4.897883244144765%2C52.37496348620152&" +
                    "fields=%7Bincidents%7Btype%2Cgeometry%7Btype%2Ccoordinates%7D%2Cproperties%7BiconCategory%7D%7D%7D&" +
                    "language=en-GB&" +
                    "categoryFilter=0%2C1%2C2%2C3%2C4%2C5%2C6%2C7%2C8%2C9%2C10%2C11%2C14&" +
                    "timeValidityFilter=present&" +
                    String.format("key=%s",apiKey));

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

    @Test
    @DisplayName("Rest Template Test")
    public void testRestTemplate() throws JSONException, UnsupportedEncodingException {
        String bbox = "4.8854592519716675,52.36934334773164,4.897883244144765,52.37496348620152";
        String fields = "{incidents{type,geometry{type,coordinates}}}";
        String url = BASE_URL +
                    "?bbox=" + bbox +
                    "&fields=" + fields +
                    "&categoryFilter=0,1,2,3,4,5,6,7,8,9,10,11,14" +
                    "&language=en-GB" +
                    "&timeValidityFilter=present" +
                    "&key=" + apiKey;

        String response = restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);
        String jsonRes = new JSONObject(response).toString(2);
        System.out.println(jsonRes);
    }
}
