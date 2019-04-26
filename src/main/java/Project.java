import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class Project {
    String name;
    String projectId;

    public Project(String name) throws Exception {
        String jsonResult;
        this.name = name;

        String requestJson = "{\"name\":\"" + name + "\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        jsonResult = Main.getRestTemplate().postForObject(Main.makeUrl("/v2/projects"), entity, String.class);

        JSONObject obj = new JSONObject(jsonResult);
        projectId= obj.getString("project_id");
    }

    public String getName() {
        return name;
    }

    public String getProjectId() {
        return projectId;
    }
}
