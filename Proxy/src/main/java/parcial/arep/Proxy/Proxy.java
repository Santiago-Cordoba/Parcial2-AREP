package parcial.arep.Proxy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class Proxy {
    private static final String USER_AGENT = "Mozilla/5.0";
    private int pivot = 0;

    @GetMapping("/factors")
    public ResponseEntity<?> factors(@RequestParam Integer value) throws IOException {
        String response = "";
        if(pivot%2==0){
            response = HttpConnection("http://localhost:1000/factors?"+"value=" + value);
        }else{
            response = HttpConnection("http://localhost:1000/factors?"+"value=" + value);
        }
        pivot+=1;
        return ResponseEntity.ok(response);
    }

    @GetMapping("/primes")
    public ResponseEntity<?> primes(@RequestParam Integer value) throws IOException {
        String response = "";
        if(pivot%2==0){
            response = HttpConnection("http://localhost:1000/primes?value=" + value);
        }else{
            response = HttpConnection("http://localhost:1000/primes?value=" + value);
        }
        return ResponseEntity.ok(response);
    }

    private String HttpConnection(String GET_URL) throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
            System.out.println("GET request not worked");
            return "GET request not worked";
        }
    }


}
