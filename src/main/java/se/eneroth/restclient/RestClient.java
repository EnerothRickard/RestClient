package se.eneroth.restclient;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class RestClient {

	public static void main(String[] args) {
        //GET
        RestTemplate restTemplate = new RestTemplate();
        Greeting page = restTemplate.getForObject("http://localhost:8080/rest/json", Greeting.class);
        System.out.println(page.getGreeting());
        System.out.println(page.getVersion());

        //POST
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("greeting", "value1");
        body.add("version", "value2");
        body.add("name", "value3");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<?> httpEntity = new HttpEntity<Object>(body, requestHeaders);

        ResponseEntity model =
                restTemplate.exchange("http://localhost:8080/rest/jsonpost", HttpMethod.POST, httpEntity, Greeting.class);

        System.out.println(((Greeting) model.getBody()).getGreeting());
        //System.out.println(((Greeting) model.getBody()).getVersion());
        System.out.println(((Greeting) model.getBody()).getName());
    }
}