package puc.tcc.contracts.api.client.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class AuthClient {

    private static final String URL = "http://tcc-puc-auth.herokuapp.com/auth/get-info";

    public UserResponse validate(String token) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UserResponse> response = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity(headers), UserResponse.class);
            log.info("AUTHORIZED!");
            return response.getBody();
        } catch (Exception e) {
            log.error("UNAUTHORIZED! message={}", e.getMessage());
            return null;
        }
    }

}
