import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public abstract class Entity{

    protected String m_name;
    protected String m_entity_id;
    protected String m_base_url;

    protected Entity(String name) {
        m_name     = name;
        m_base_url = Main.m_base_URL;
    }

    protected Entity(String name, String entity_id) {
        m_name      = name;
        m_entity_id = entity_id;
        m_base_url  = Main.m_base_URL;
    }

    protected JSONObject create(JSONObject req){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(req.toString(), headers);

        String jsonResult = Main.getRestTemplate().postForObject(m_base_url, entity, String.class);
        return new JSONObject(jsonResult);
    }

    protected void delete(){
        Main.getRestTemplate().delete(m_base_url + "/" + m_entity_id);
    }

    public String getName() {
        return m_name;
    }

    public String getEntityId() {
        return m_entity_id;
    }
}