
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class Project extends Entity{

    public String makeUrl(String path){
        return Main.makeUrl("/v2/projects") + path;
    }

    public Project(String name) throws Exception {
        this.m_name = name;
        create();
    }

    public void create()
    {
        String jsonResult;


        String requestJson = "{\"name\":\"" + this.m_name + "\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        jsonResult = Main.getRestTemplate().postForObject(makeUrl(""), entity, String.class);

        JSONObject obj = new JSONObject(jsonResult);
        m_project_id= obj.getString("project_id");
    }

    public void delete()
    {
        Main.getRestTemplate().delete(makeUrl("/" + m_project_id));
    }

    public String getName() {
        return m_name;
    }

    public String getProjectId() {
        return m_project_id;
    }
}
