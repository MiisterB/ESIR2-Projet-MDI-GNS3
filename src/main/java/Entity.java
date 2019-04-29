import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public abstract class Entity{

    protected String m_name;
    protected String m_entity_id;

    protected Entity(String name) {
        m_name = name;
    }

    protected JSONObject create(JSONObject requestJson){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(requestJson.toString(), headers);

        String jsonResult = Main.getRestTemplate().postForObject(makeUrl(""), entity, String.class);
        return new JSONObject(jsonResult);
    }

    protected void delete(){
        Main.getRestTemplate().delete(makeUrl("/" + m_entity_id));
    }

    protected String makeUrl(String path){
        return Main.makeUrl(path);
    }

    public String getName() {
        return m_name;
    }

    public String getEntityId() {
        return m_entity_id;
    }
}