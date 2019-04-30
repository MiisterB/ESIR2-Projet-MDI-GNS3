package lib;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import java.util.List;

public abstract class RestEntity {

    RestTemplate restTemplate;
    String m_entity_id;
    String m_base_url;

    abstract RestEntity createInstance(List<String> params);
    abstract RestEntity getInstance(JSONObject e);

    JSONObject create(JSONObject req){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(req.toString(), headers);

        String jsonResult = restTemplate.postForObject(m_base_url, entity, String.class);
        return new JSONObject(jsonResult);
    }

    public void delete(){
        restTemplate.delete(m_base_url + "/" + m_entity_id);
    }

    public String getId() {
        return m_entity_id;
    }
}