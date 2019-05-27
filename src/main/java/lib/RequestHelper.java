package lib;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


public class RequestHelper {

    private static RestTemplate restTemplate = new RestTemplate();
    private static JSONObject   emptyReq     = new JSONObject();

    public static JSONObject post(String url){
        String jsonResult = restTemplate.postForObject(url, emptyReq, String.class);
        return new JSONObject(jsonResult);
    }

    public static JSONObject post(String url, JSONObject req){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(req.toString(), headers);

        String jsonResult = restTemplate.postForObject(url, entity, String.class);
        return new JSONObject(jsonResult);
    }


    public static JSONObject get(String url){
        String jsonResult = restTemplate.getForObject(url, String.class);
        return new JSONObject(jsonResult);
    }

}
