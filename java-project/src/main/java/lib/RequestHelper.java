package lib;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


public class RequestHelper {

    private static RestTemplate restTemplate = new RestTemplate();
    private static JSONObject   emptyReq     = new JSONObject();

    private static  HttpEntity<String> getJSONEntity(JSONObject req){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(req.toString(), headers);
    }

    public static JSONObject post(String url){
        HttpEntity<String> entity = getJSONEntity(emptyReq);
        String jsonResult = restTemplate.postForObject(url, entity, String.class);
        return new JSONObject(jsonResult);
    }

    public static JSONObject post(String url, JSONObject req){
        HttpEntity<String> entity = getJSONEntity(req);
        String jsonResult = restTemplate.postForObject(url, entity, String.class);
        return new JSONObject(jsonResult);
    }

    public static JSONObject get(String url){
        String jsonResult = restTemplate.getForObject(url, String.class);
        return new JSONObject(jsonResult);
    }

    public static JSONArray getArray(String url){
        String jsonResult = restTemplate.getForObject(url, String.class);
        return new JSONArray(jsonResult);
    }

    public static void delete(String url){
        restTemplate.delete(url);
    }

    public static void put(String url, JSONObject req){
        HttpEntity<String> entity = getJSONEntity(req);
        restTemplate.put(url, entity);
    }

}
