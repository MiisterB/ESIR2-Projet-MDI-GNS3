package lib;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class Appliance {

    RestTemplate restTemplate;
    String m_base_url;
    String m_project_url;

    Appliance(String base_url, String project_url) {
        restTemplate = new RestTemplate();
        m_base_url = base_url;
        m_project_url = project_url;
    }


}
